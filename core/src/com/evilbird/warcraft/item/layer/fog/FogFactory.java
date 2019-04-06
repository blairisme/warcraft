/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.fog;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.layer.LayerUtils;
import com.evilbird.warcraft.item.layer.common.BitMatrix;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.engine.common.graphics.TextureUtils.getRegion;
import static com.evilbird.engine.common.graphics.TextureUtils.region;
import static com.evilbird.warcraft.item.layer.common.BitMatrix.matrix;

/**
 * Instances of this factory create {@link Fog} instances.
 *
 * @author Blair Butterworth
 */
//TODO: Feature: Add support for transparent fog
public class FogFactory implements IdentifiedAssetProvider<Item>
{
    private static final String TERRAIN = "data/textures/neutral/winter/terrain.png";
    private EventQueue events;
    private AssetManager assets;

    @Inject
    public FogFactory(Device device, EventQueue events) {
        this.assets = device.getAssetStorage();
        this.events = events;
    }

    @Override
    public void load() {
        assets.load(TERRAIN, Texture.class);
    }

    @Override
    public Item get(Identifier identifier) {
        Validate.isInstanceOf(LayerIdentifier.class, identifier);
        LayerIdentifier layerIdentifier = (LayerIdentifier)identifier;

        switch (layerIdentifier.getType()) {
            case OpaqueFog: return getOpaqueFog(layerIdentifier);
            case TransparentFog: return getTransparentFog(layerIdentifier);
            default: throw new UnknownEntityException(identifier);
        }
    }

    private Fog getOpaqueFog(LayerIdentifier identifier) {
        Fog fog = new Fog();
        fog.setEvents(events);
        fog.setSkin(getSkin());
        fog.setIdentifier(identifier);
        fog.setType(LayerType.OpaqueFog);
        fog.setLayer(LayerUtils.getLayer(identifier));
        fog.setVisible(true);
        fog.setSelected(false);
        fog.setSelectable(false);
        fog.setTouchable(Touchable.disabled);
        return fog;
    }

    private Fog getTransparentFog(LayerIdentifier identifier) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getOpaqueStyle());
        return skin;
    }

    private FogStyle getOpaqueStyle() {
        Texture opaque = getOpaqueTexture();
        Texture terrain = getTerrainTexture();

        FogStyle result = new FogStyle();
        result.full = cell(new TextureRegion(opaque));
        result.empty = null;

        result.edges = new HashMap<>();
        result.edges.put(matrix(3, "0,1,1,0,1,1,0,1,1"), cell(terrain, 192, 0, 32, 32)); //left
        result.edges.put(matrix(3, "1,1,0,1,1,0,1,1,0"), cell(terrain, 128, 0, 32, 32)); //right

        result.edges.put(matrix(3, "1,1,1,1,1,1,0,0,0"), cell(terrain, 256, 0, 32, 32)); //top
        result.edges.put(matrix(3, "0,0,0,1,1,1,1,1,0"), cell(terrain, 64, 0, 32, 32)); //bottom

        result.edges.put(matrix(3, "1,1,1,1,1,1,1,1,0"), cell(terrain, 32, 0, 32, 32)); //bottom right internal
        result.edges.put(matrix(3, "1,1,1,1,1,1,0,1,1"), cell(terrain, 96, 0, 32, 32)); //bottom left internal
        result.edges.put(matrix(3, "1,1,0,1,1,1,1,1,1"), cell(terrain, 224, 0, 32, 32)); //top right internal
        result.edges.put(matrix(3, "0,1,1,1,1,1,1,1,1"), cell(terrain, 288, 0, 32, 32)); //top left internal

        result.edges.put(matrix(3, "0,1,0,0,1,0,0,1,0"), cell(terrain, 160, 0, 32, 32)); //vertical peninsula

        result.edges.put(matrix(3, "1,1,0,1,1,0,0,0,0"), cell(terrain, 320, 0, 32, 32)); //bottom right external
        result.edges.put(matrix(3, "0,1,1,0,1,1,0,0,0"), cell(terrain, 352, 0, 32, 32)); //bottom left external
        result.edges.put(matrix(3, "0,0,0,1,1,0,1,1,0"), cell(terrain, 384, 0, 32, 32)); //top right external
        result.edges.put(matrix(3, "0,0,0,0,1,1,0,1,1"), cell(terrain, 416, 0, 32, 32)); //top left external


        result.edges.put(matrix(3, "0,0,1,0,1,0,1,0,0"), cell(terrain, 448, 0, 32, 32)); //bottom left top right
        result.edges.put(matrix(3, "1,0,0,0,1,0,0,0,1"), cell(terrain, 480, 0, 32, 32)); //top left bottom right


        /*
         * 0 0 1
         * 0 1 0
         * 1 0 0
         */


//        result.bottomRightInternal = createCell(new TextureRegion(terrainTexture, 32, 0, 32, 32));
//        result.bottom = createCell(new TextureRegion(terrainTexture, 64, 0, 32, 32));
//        result.bottomLeftInternal = createCell(new TextureRegion(terrainTexture, 96, 0, 32, 32));
//        result.right = createCell(new TextureRegion(terrainTexture, 128, 0, 32, 32));
//
//        result.left = createCell(new TextureRegion(terrainTexture, 192, 0, 32, 32));
//        result.topRightInternal = createCell(new TextureRegion(terrainTexture, 224, 0, 32, 32));
//        result.top = createCell(new TextureRegion(terrainTexture, 256, 0, 32, 32));
//        result.topLeftInternal = createCell(new TextureRegion(terrainTexture, 288, 0, 32, 32));
//
//        result.bottomRightExternal = createCell(new TextureRegion(terrainTexture, 320, 0, 32, 32));
//        result.bottomLeftExternal = createCell(new TextureRegion(terrainTexture, 352, 0, 32, 32));
//        result.topRightExternal = createCell(new TextureRegion(terrainTexture, 384, 0, 32, 32));
//        result.topLeftExternal = createCell(new TextureRegion(terrainTexture, 416, 0, 32, 32));

        return result;
    }

    private Texture getOpaqueTexture() {
        Pixmap pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fillRectangle(0, 0, 32, 32);
        return new Texture(pixmap);
    }

    private Texture getTerrainTexture() {
        Texture texture = assets.get(TERRAIN, Texture.class);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        return texture;
    }

    private Cell cell(TextureRegion region) {
        TiledMapTile tile = new StaticTiledMapTile(region);
        Cell cell = new Cell();
        cell.setTile(tile);
        return cell;
    }

    private Cell cell(Texture texture, int x, int y, int width, int height) {
        TextureRegion region = new TextureRegion(texture, x, y, width, height);
        TiledMapTile tile = new StaticTiledMapTile(region);
        Cell cell = new Cell();
        cell.setTile(tile);
        return cell;
    }
}


