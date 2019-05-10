/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.maps;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.Iterator;

/**
 * Instances of this class allow a {@link TiledMapTileLayer} to used by the
 * for-each operator, looping through the {@link MapLayerEntry entries}
 * contained in the layer.
 *
 * @author Blair Butterworth
 */
public class MapLayerIterable implements Iterable<MapLayerEntry>
{
    private TiledMapTileLayer layer;

    public MapLayerIterable(TiledMapTileLayer layer) {
        this.layer = layer;
    }

    @Override
    public Iterator<MapLayerEntry> iterator() {
        return new MapLayerIterator(layer);
    }
}