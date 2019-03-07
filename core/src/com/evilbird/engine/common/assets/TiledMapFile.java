/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.assets;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * Instances of this class represent a {@link TiledMap} loaded from a file.
 *
 * @author Blair Butterworth
 */
public class TiledMapFile
{
    private TiledMap map;
    private String file;

    public TiledMapFile(TiledMap map, String file) {
        this.map = map;
        this.file = file;
    }

    public MapLayers getLayers () {
        return map.getLayers();
    }

    public String getFile() {
        return file;
    }
}
