/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.evilbird.engine.common.maps.TiledMapFile;
import com.evilbird.engine.common.maps.TiledMapLoader;

/**
 * Instances of this class contain common functions for working with
 * {@link Layer Layers}.
 *
 * @author Blair Butterworth
 */
public class LayerUtils
{
    private LayerUtils() {
    }

    public static TiledMapTileLayer getLayer(LayerIdentifier type) {
        TiledMapTileLayer layer = type.getLayer();
        if (layer == null) {
            TiledMapLoader loader = new TiledMapLoader();
            TiledMapFile map = loader.load(type.getFile());
            MapLayers layers = map.getLayers();
            layer = (TiledMapTileLayer)layers.get(type.getName());
        }
        return layer;
    }

    public static Cell cell(Texture texture) {
        return cell(new TextureRegion(texture));
    }

    public static Cell cell(Texture texture, int x, int y, int width, int height) {
        return cell(new TextureRegion(texture, x, y, width, height));
    }

    public static Cell cell(TextureRegion region) {
        TiledMapTile tile = new StaticTiledMapTile(region);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(tile);
        return cell;
    }
}
