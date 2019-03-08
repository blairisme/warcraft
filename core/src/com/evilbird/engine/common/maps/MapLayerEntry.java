/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.maps;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

/**
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
