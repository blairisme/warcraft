/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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