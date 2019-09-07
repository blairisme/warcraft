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
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
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

    public static Cell cell(TextureRegion region) {
        TiledMapTile tile = new StaticTiledMapTile(region);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(tile);
        return cell;
    }

    public static Cell unpaddedCell(Texture texture, int x, int y, int width, int height) {
        int padding = 2;
        int axisPadding = padding + padding;
        int xIndex = x / width;
        int yIndex = y / height;
        int paddedX = x + (xIndex * axisPadding) + padding;
        int paddedY = y + (yIndex * axisPadding) + padding;
        return cell(new TextureRegion(texture, paddedX, paddedY, width, height));
    }

    public static GridPoint2 toCellDimensions(TiledMapTileLayer layer, Vector2 vector) {
        GridPoint2 result = new GridPoint2();
        result.x = vector.x != 0 ? Math.round(vector.x / layer.getTileWidth()) : 0;
        result.y = vector.y != 0 ? Math.round(vector.y / layer.getTileHeight()) : 0;
        return result;
    }

    public static int toCellDimensions(TiledMapTileLayer layer, int value) {
        return value != 0 ? Math.round(value / layer.getTileWidth()) : 0;
    }

    public static boolean withinBounds(TiledMapTileLayer layer, int x, int y) {
        return x >= 0 && y >= 0 && x < layer.getWidth() && y < layer.getHeight();
    }
}
