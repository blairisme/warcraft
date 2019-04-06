/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.fog;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.evilbird.engine.common.collection.BitMatrix;

import java.util.Map;

/**
 * Instances of this simple object represent specify the style of a fog
 * layer cell and the cells that surround it.
 *
 * @author Blair Butterworth
 */
public class FogStyle
{
    public Cell empty;
    public Cell full;
    public Map<BitMatrix, Cell> edges;
}
