/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.wall;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.collection.BitMatrix;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.layer.LayerGroupStyle;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerUtils;
import com.evilbird.warcraft.state.WarcraftContext;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this factory create {@link Wall} instances, loading the
 * appropriate assets to display the wall and its cells in different
 * states.
 *
 * @author Blair Butterworth
 */
public class WallFactory implements GameFactory<Wall>
{
    private AssetManager manager;
    private WallAssets assets;

    @Inject
    public WallFactory(Device device) {
        this(device.getAssetStorage());
    }

    public WallFactory(AssetManager manager) {
        this.manager = manager;
    }

    @Override
    public void load(GameContext context) {
        Validate.isInstanceOf(WarcraftContext.class, context);
        load((WarcraftContext)context);
    }

    private void load(WarcraftContext context) {
        assets = new WallAssets(manager, context);
        assets.load();;
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }

    @Override
    public Wall get(Identifier identifier) {
        Validate.isInstanceOf(LayerIdentifier.class, identifier);
        LayerIdentifier layerIdentifier = (LayerIdentifier)identifier;

        Wall wall = new Wall(getSkin());
        wall.setIdentifier(layerIdentifier);
        wall.setType(layerIdentifier.getType());
        wall.setLayer(LayerUtils.getLayer(layerIdentifier));
        wall.setVisible(true);
        wall.setTouchable(Touchable.childrenOnly);
        return wall;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getStyle());
        return skin;
    }

    private LayerGroupStyle getStyle() {
        Texture terrain = assets.getTerrainTexture();
        LayerGroupStyle forestStyle = new LayerGroupStyle();
        forestStyle.empty = LayerUtils.unpaddedCell(terrain, 256, 128, 32, 32);
        forestStyle.patterns = getCellStyles(terrain);
        return forestStyle;
    }

    private Map<BitMatrix, TiledMapTileLayer.Cell> getCellStyles(Texture texture) {
        Map<BitMatrix, TiledMapTileLayer.Cell> styles = new HashMap<>();
        return styles;
    }
}
