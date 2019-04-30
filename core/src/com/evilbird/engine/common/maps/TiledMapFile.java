/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.maps;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;

/**
 * Instances of this class represent a {@link TiledMap} loaded from a file.
 *
 * @author Blair Butterworth
 */
public class TiledMapFile
{
    private String file;
    private TiledMap map;
    private GridPoint2 mapSize;
    private GridPoint2 tileSize;

    public TiledMapFile(TiledMap map, String file) {
        this.map = map;
        this.file = file;
        this.mapSize = new GridPoint2();
        this.tileSize = new GridPoint2();
        initialize();
    }

    public void dispose() {
        map.dispose();
    }

    public MapLayers getLayers () {
        return map.getLayers();
    }

    public String getFile() {
        return file;
    }

    public GridPoint2 getMapSize() {
        return mapSize;
    }

    public GridPoint2 getTileSize() {
        return tileSize;
    }

    private void initialize() {
        for (TiledMapTileLayer layer: map.getLayers().getByType(TiledMapTileLayer.class)) {
            mapSize.x = Math.max(mapSize.x, layer.getWidth());
            mapSize.y = Math.max(mapSize.y, layer.getHeight());
            tileSize.x = Math.max(tileSize.x, (int)layer.getTileWidth());
            tileSize.y = Math.max(tileSize.y, (int)layer.getTileHeight());
        }
    }
}
