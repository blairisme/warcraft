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