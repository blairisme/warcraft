/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.maps;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.GridPoint2;

/**
 * Represents an element contained in a{@link MapLayer}. Methods are provided
 * to obtain the elements position within the layer and to access its
 * {@link Cell} which describes its visual appearance.
 *
 * @author Blair Butterworth
 */
public class MapLayerEntry
{
    private Cell cell;
    private GridPoint2 position;

    public MapLayerEntry(Cell cell, int x, int y) {
        this.cell = cell;
        this.position = new GridPoint2(x, y);
    }

    public Cell getCell() {
        return cell;
    }

    public GridPoint2 getPosition() {
        return position;
    }
}
