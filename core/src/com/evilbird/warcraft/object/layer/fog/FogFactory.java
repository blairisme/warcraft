/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.fog;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.collection.BitMatrix;
import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.layer.LayerGroupStyle;
import com.evilbird.warcraft.object.layer.LayerIdentifier;
import com.evilbird.warcraft.object.layer.LayerType;
import com.evilbird.warcraft.object.layer.LayerUtils;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.engine.common.collection.BitMatrix.matrix3;
import static com.evilbird.warcraft.object.layer.LayerUtils.cell;
import static com.evilbird.warcraft.object.layer.LayerUtils.unpaddedCell;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.BackSlash;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.Bottom;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.BottomLeftExternal;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.BottomLeftInternal;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.BottomRightExternal;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.BottomRightInterval;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.Empty;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.ForwardSlash;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.Full;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.Left;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.Right;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.Top;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.TopLeftExternal;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.TopLeftInternal;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.TopRightExternal;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.TopRightInternal;

/**
 * Instances of this factory create {@link Fog} instances.
 *
 * @author Blair Butterworth
 */
public class FogFactory implements GameFactory<Fog>
{
    private EventQueue events;
    private AssetManager manager;
    private FogAssets assets;
    private WarcraftPreferences preferences;

    @Inject
    public FogFactory(Device device, EventQueue events, WarcraftPreferences preferences) {
        this(device.getAssetStorage(), events, preferences);
    }

    public FogFactory(AssetManager manager, EventQueue events, WarcraftPreferences preferences) {
        this.manager = manager;
        this.events = events;
        this.preferences = preferences;
    }

    @Override
    public void load(GameContext context) {
        assets = new FogAssets(manager);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }

    @Override
    public Fog get(Identifier identifier) {
        Validate.isInstanceOf(LayerIdentifier.class, identifier);
        LayerIdentifier layerIdentifier = (LayerIdentifier)identifier;

        switch (layerIdentifier.getType()) {
            case OpaqueFog: return getOpaqueFog(layerIdentifier);
            case TransparentFog: return getTransparentFog(layerIdentifier);
            default: throw new UnknownEntityException(identifier);
        }
    }

    private Fog getOpaqueFog(LayerIdentifier identifier) {
        Fog fog = new Fog(getOpaqueSkin(), events);
        fog.setIdentifier(identifier);
        fog.setLayer(LayerUtils.getLayer(identifier));
        fog.setTouchable(Touchable.childrenOnly);
        fog.setType(LayerType.OpaqueFog);
        fog.setVisible(! preferences.isRevealMapCheatEnabled());
        return fog;
    }

    private Fog getTransparentFog(LayerIdentifier identifier) {
        Fog fog = new ConcealingFog(getTransparentSkin(), events);
        fog.setIdentifier(identifier);
        fog.setLayer(LayerUtils.getLayer(identifier));
        fog.setTouchable(Touchable.childrenOnly);
        fog.setType(LayerType.TransparentFog);
        fog.setVisible(! preferences.isRevealMapCheatEnabled());
        return fog;
    }

    private Skin getOpaqueSkin() {
        Skin skin = new Skin();
        skin.add("default", getOpaqueStyle());
        return skin;
    }

    private Skin getTransparentSkin() {
        Skin skin = new Skin();
        skin.add("default", getTransparentStyle());
        return skin;
    }

    private LayerGroupStyle getOpaqueStyle() {
        LayerGroupStyle result = new LayerGroupStyle();
        result.empty = null;
        result.full = cell(assets.getOpaqueTexture());
        result.patterns = getEdgeStyles(assets.getFogTexture(), 0);
        return result;
    }

    private LayerGroupStyle getTransparentStyle() {
        LayerGroupStyle result = new LayerGroupStyle();
        result.empty = null;
        result.full = cell(assets.getTransparentTexture());
        result.patterns = getEdgeStyles(assets.getFogTexture(), 36);
        return result;
    }

    private Map<BitMatrix, Cell> getEdgeStyles(Texture texture, int y) {
        Map<BitMatrix, Cell> styles = new HashMap<>();
        addStraightEdges(styles, texture, y);
        addInternalCorners(styles, texture, y);
        addExternalCorners(styles, texture, y);
        addOppositeCorners(styles, texture, y);
        addFull(styles, texture, y);
        return styles;
    }

    private void addStraightEdges(Map<BitMatrix, Cell> styles, Texture texture, int y) {
        addLeftEdge(styles, texture, y);
        addRightEdge(styles, texture, y);
        addTopEdge(styles, texture, y);
        addBottomEdge(styles, texture, y);
    }

    private void addLeftEdge(Map<BitMatrix, Cell> styles, Texture texture, int y) {
        Cell style = unpaddedCell(texture, 192, y, 32, 32);
        styles.put(matrix3("0,1,1,0,1,1,0,1,1", Left), style);
        styles.put(matrix3("0,1,1,0,1,1,1,1,1", Left), style);
        styles.put(matrix3("1,1,1,0,1,1,0,1,1", Left), style);
        styles.put(matrix3("0,1,1,1,1,1,0,1,1", Left), style);
    }

    private void addRightEdge(Map<BitMatrix, Cell> styles, Texture texture, int y) {
        Cell style = unpaddedCell(texture, 128, y, 32, 32);
        styles.put(matrix3("1,1,0,1,1,0,1,1,0", Right), style);
        styles.put(matrix3("1,1,0,1,1,0,1,1,1", Right), style);
        styles.put(matrix3("1,1,1,1,1,0,1,1,0", Right), style);
        styles.put(matrix3("1,1,0,1,1,1,1,1,0", Right), style);
    }

    private void addTopEdge(Map<BitMatrix, Cell> styles, Texture texture, int y) {
        Cell style = unpaddedCell(texture, 256, y, 32, 32);
        styles.put(matrix3("1,1,1,1,1,1,0,0,0", Top), style);
        styles.put(matrix3("1,1,1,1,1,1,1,0,0", Top), style);
        styles.put(matrix3("1,1,1,1,1,1,0,0,1", Top), style);
        styles.put(matrix3("1,1,1,1,1,1,0,1,0", Top), style);
    }

    private void addBottomEdge(Map<BitMatrix, Cell> styles, Texture texture, int y) {
        Cell style = unpaddedCell(texture, 64, y, 32, 32);
        styles.put(matrix3("0,0,0,1,1,1,1,1,1", Bottom), style);
        styles.put(matrix3("1,0,0,1,1,1,1,1,1", Bottom), style);
        styles.put(matrix3("0,0,1,1,1,1,1,1,1", Bottom), style);
        styles.put(matrix3("0,1,0,1,1,1,1,1,1", Bottom), style);
    }

    private void addInternalCorners(Map<BitMatrix, Cell> styles, Texture texture, int y) {
        styles.put(matrix3("1,1,0,1,1,1,1,1,1", BottomRightInterval), unpaddedCell(texture, 32, y, 32, 32));
        styles.put(matrix3("0,1,1,1,1,1,1,1,1", BottomLeftInternal), unpaddedCell(texture, 96, y, 32, 32));
        styles.put(matrix3("1,1,1,1,1,1,1,1,0", TopRightInternal), unpaddedCell(texture, 224, y, 32, 32));
        styles.put(matrix3("1,1,1,1,1,1,0,1,1", TopLeftInternal), unpaddedCell(texture, 288, y, 32, 32));
    }

    private void addExternalCorners(Map<BitMatrix, Cell> styles, Texture texture, int y) {
        addTopLeftExternalCorner(styles, texture, y);
        addTopRightExternalCorner(styles, texture, y);
        addBottomLeftExternalCorner(styles, texture, y);
        addBottomRightExternalCorner(styles, texture, y);
    }

    private void addTopLeftExternalCorner(Map<BitMatrix, Cell> styles, Texture texture, int y) {
        Cell style = unpaddedCell(texture, 416, y, 32, 32);
        styles.put(matrix3("0,1,1,0,1,1,0,0,0", TopLeftExternal), style);
        styles.put(matrix3("1,1,1,0,1,1,0,0,0", TopLeftExternal), style);
        styles.put(matrix3("0,1,1,1,1,1,0,0,0", TopLeftExternal), style);
        styles.put(matrix3("0,1,1,0,1,1,1,0,0", TopLeftExternal), style);
        styles.put(matrix3("0,1,1,0,1,1,0,1,0", TopLeftExternal), style);
        styles.put(matrix3("0,1,1,0,1,1,0,0,1", TopLeftExternal), style);
        styles.put(matrix3("0,1,1,1,1,1,1,0,0", TopLeftExternal), style);
        styles.put(matrix3("0,1,1,0,1,1,1,1,0", TopLeftExternal), style);
        styles.put(matrix3("0,1,1,1,1,1,0,0,1", TopLeftExternal), style);
        styles.put(matrix3("1,1,1,0,1,1,0,1,0", TopLeftExternal), style);
        styles.put(matrix3("1,1,1,0,1,1,0,0,1", TopLeftExternal), style);
    }

    private void addTopRightExternalCorner(Map<BitMatrix, Cell> styles, Texture texture, int y) {
        Cell style = unpaddedCell(texture, 384, y, 32, 32);
        styles.put(matrix3("1,1,0,1,1,0,0,0,0", TopRightExternal), style);
        styles.put(matrix3("1,1,1,1,1,0,0,0,0", TopRightExternal), style);
        styles.put(matrix3("1,1,0,1,1,1,0,0,0", TopRightExternal), style);
        styles.put(matrix3("1,1,0,1,1,0,0,0,1", TopRightExternal), style);
        styles.put(matrix3("1,1,0,1,1,0,0,1,0", TopRightExternal), style);
        styles.put(matrix3("1,1,0,1,1,0,1,0,0", TopRightExternal), style);
        styles.put(matrix3("1,1,0,1,1,1,0,0,1", TopRightExternal), style);
        styles.put(matrix3("1,1,0,1,1,0,0,1,1", TopRightExternal), style);
        styles.put(matrix3("1,1,1,1,1,0,0,1,0", TopRightExternal), style);
        styles.put(matrix3("1,1,0,1,1,1,1,0,0", TopRightExternal), style);
        styles.put(matrix3("1,1,1,1,1,0,1,0,0", TopRightExternal), style);
    }

    private void addBottomLeftExternalCorner(Map<BitMatrix, Cell> styles, Texture texture, int y) {
        Cell style = unpaddedCell(texture, 352, y, 32, 32);
        styles.put(matrix3("0,0,0,0,1,1,0,1,1", BottomLeftExternal), style);
        styles.put(matrix3("0,0,0,0,1,1,1,1,1", BottomLeftExternal), style);
        styles.put(matrix3("0,0,0,1,1,1,0,1,1", BottomLeftExternal), style);
        styles.put(matrix3("1,0,0,0,1,1,0,1,1", BottomLeftExternal), style);
        styles.put(matrix3("0,1,0,0,1,1,0,1,1", BottomLeftExternal), style);
        styles.put(matrix3("0,0,1,0,1,1,0,1,1", BottomLeftExternal), style);
        styles.put(matrix3("1,0,0,1,1,1,0,1,1", BottomLeftExternal), style);
        styles.put(matrix3("1,1,0,0,1,1,0,1,1", BottomLeftExternal), style);
        styles.put(matrix3("0,1,0,0,1,1,1,1,1", BottomLeftExternal), style);
        styles.put(matrix3("0,0,1,1,1,1,0,1,1", BottomLeftExternal), style);
        styles.put(matrix3("0,0,1,0,1,1,1,1,1", BottomLeftExternal), style);
    }

    private void addBottomRightExternalCorner(Map<BitMatrix, Cell> styles, Texture texture, int y) {
        Cell style = unpaddedCell(texture, 320, y, 32, 32);
        styles.put(matrix3("0,0,0,1,1,0,1,1,0", BottomRightExternal), style);
        styles.put(matrix3("1,0,0,1,1,0,1,1,0", BottomRightExternal), style);
        styles.put(matrix3("0,1,0,1,1,0,1,1,0", BottomRightExternal), style);
        styles.put(matrix3("0,0,1,1,1,0,1,1,0", BottomRightExternal), style);
        styles.put(matrix3("0,0,0,1,1,1,1,1,0", BottomRightExternal), style);
        styles.put(matrix3("0,0,0,1,1,0,1,1,1", BottomRightExternal), style);
        styles.put(matrix3("0,1,1,1,1,0,1,1,0", BottomRightExternal), style);
        styles.put(matrix3("0,0,1,1,1,1,1,1,0", BottomRightExternal), style);
        styles.put(matrix3("0,1,0,1,1,0,1,1,1", BottomRightExternal), style);
        styles.put(matrix3("1,0,0,1,1,1,1,1,0", BottomRightExternal), style);
        styles.put(matrix3("1,0,0,1,1,0,1,1,1", BottomRightExternal), style);
    }

    private void addOppositeCorners(Map<BitMatrix, Cell> styles, Texture texture, int y) {
        styles.put(matrix3("1,1,0,1,1,1,0,1,1", ForwardSlash), unpaddedCell(texture, 480, y, 32, 32));
        styles.put(matrix3("0,1,1,1,1,1,1,1,0", BackSlash), unpaddedCell(texture, 448, y, 32, 32));
    }

    private void addFull(Map<BitMatrix, Cell> styles, Texture texture, int y) {
        styles.put(matrix3("1,1,1,1,1,1,1,1,1", Full), unpaddedCell(texture, 0, y, 32, 32));
        styles.put(matrix3("0,0,0,0,0,0,0,0,0", Empty), null);
    }

//    private void addEmpty(Map<BitMatrix, Cell> styles, Texture texture, int y) {
//        Cell empty = cell(getRectangle(32, 32, Color.BLUE));
//        styles.put(matrix3("0,1,0,0,1,0,0,1,0"), empty);
//        styles.put(matrix3("0,1,1,0,1,0,0,1,0"), empty);
//        styles.put(matrix3("1,1,0,0,1,0,0,1,0"), empty);
//        styles.put(matrix3("0,1,0,0,1,0,1,1,0"), empty);
//        styles.put(matrix3("0,1,0,0,1,0,0,1,1"), empty);
//
//        styles.put(matrix3("0,1,0,0,1,0,0,0,0"), empty);
//        styles.put(matrix3("1,1,0,0,1,0,0,0,0"), empty);
//        styles.put(matrix3("0,1,1,0,1,0,0,0,0"), empty);
//        styles.put(matrix3("1,1,1,0,1,0,0,0,0"), empty);
//
//        styles.put(matrix3("0,0,0,0,1,0,0,1,0"), empty);
//        styles.put(matrix3("0,0,0,0,1,0,1,1,0"), empty);
//        styles.put(matrix3("0,0,0,0,1,0,0,1,1"), empty);
//        styles.put(matrix3("0,0,0,0,1,0,1,1,1"), empty);
//
//        styles.put(matrix3("0,0,0,1,1,1,0,0,0"), empty);
//
//        styles.put(matrix3("0,0,0,0,1,1,0,0,0"), empty);
//        styles.put(matrix3("0,0,1,0,1,1,0,0,1"), empty);
//        styles.put(matrix3("0,0,0,0,1,1,0,0,1"), empty);
//        styles.put(matrix3("0,0,1,0,1,1,0,0,0"), empty);
//
//        styles.put(matrix3("0,0,0,1,1,0,0,0,0"), empty);
//        styles.put(matrix3("1,0,0,1,1,0,1,0,0"), empty);
//        styles.put(matrix3("0,0,0,1,1,0,1,0,0"), empty);
//        styles.put(matrix3("1,0,0,1,1,0,0,0,0"), empty);
//
//        styles.put(matrix3("1,0,0,0,0,0,0,0,1"), empty);
//        styles.put(matrix3("0,0,1,0,0,0,1,0,0"), empty);
//        styles.put(matrix3("0,0,0,0,1,0,0,0,0"), empty);
//
//        styles.put(matrix3("1,0,0,1,1,0,0,0,1"), empty);
//        styles.put(matrix3("1,0,0,0,1,1,0,0,1"), empty);
//        styles.put(matrix3("1,0,0,1,1,1,0,0,1"), empty);
//        styles.put(matrix3("1,1,0,0,1,1,0,0,1"), empty);
//        styles.put(matrix3("1,0,0,1,1,0,0,1,1"), empty);
//        styles.put(matrix3("1,0,0,1,1,1,0,0,0"), empty);
//        styles.put(matrix3("1,1,0,0,1,1,0,0,0"), empty);
//        styles.put(matrix3("1,0,0,0,1,0,0,0,1"), empty);
//
//        styles.put(matrix3("0,0,0,1,1,1,0,0,1"), empty);
//        styles.put(matrix3("1,0,0,0,1,0,0,0,1"), empty);
//        styles.put(matrix3("1,0,0,0,1,1,0,0,0"), empty);
//
//        styles.put(matrix3("0,1,1,0,1,0,1,1,0"), empty);
//        styles.put(matrix3("1,1,1,0,1,0,0,1,0"), empty);
//        styles.put(matrix3("0,0,0,0,1,0,0,0,1"), empty);
//        styles.put(matrix3("1,1,0,0,1,0,0,1,1"), empty);
//        styles.put(matrix3("1,1,0,0,1,1,1,1,1"), empty);
//        styles.put(matrix3("1,0,0,0,1,0,1,1,1"), empty);
//        styles.put(matrix3("0,1,0,0,1,1,0,0,1"), empty);
//    }
}


