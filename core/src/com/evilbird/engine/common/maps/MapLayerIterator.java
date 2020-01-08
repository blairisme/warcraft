/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.maps;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import java.util.Iterator;

/**
 * Instances of this iterate loop through the contents of a
 * {@link TiledMapTileLayer}.
 *
 * @author Blair Butterworth
 */
public class MapLayerIterator implements Iterator<MapLayerEntry>
{
    private TiledMapTileLayer layer;
    private MapLayerEntry next;
    private int x;
    private int y;
    private int width;
    private int height;

    public MapLayerIterator(TiledMapTileLayer layer) {
        this.layer = layer;
        this.x = -1;
        this.y = 0;
        this.width = layer.getWidth();
        this.height = layer.getHeight();
        this.next = getNext();
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public MapLayerEntry next() {
        MapLayerEntry result = next;
        next = getNext();
        return result;
    }

    private MapLayerEntry getNext() {
        Cell cell = null;
        while (y < height && cell == null) {
            y = x + 1 < width ? y : y + 1;
            x = x + 1 < width ? x + 1 : 0;
            cell = layer.getCell(x, y);
        }
        return cell != null ? new MapLayerEntry(cell, x, y) : null;
    }
}