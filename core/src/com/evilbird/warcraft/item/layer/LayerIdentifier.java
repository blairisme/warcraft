/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.engine.common.assets.TiledMapFile;
import com.evilbird.engine.item.ItemType;

public class LayerIdentifier implements ItemType
{
    private String file;
    private String name;
    private transient TiledMapTileLayer layer;

    public LayerIdentifier(TiledMapFile map, TiledMapTileLayer layer) {
        this.file = map.getFile();
        this.name = layer.getName();
        this.layer = layer;
    }

    public String getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public LayerType getType() {
        return LayerType.valueOf(name);
    }

    public TiledMapTileLayer getLayer() {
        return layer;
    }
}
