/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.forest;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.evilbird.engine.common.collection.BitMatrix;

import java.util.Map;

/**
 * Instances of this simple object represent specify the style of a forest
 * layer cell and the surrounding cells.
 * 
 * @author Blair Butterworth
 */
public class ForestStyle
{
    public Cell deforested;
    public Map<BitMatrix, Cell> trees;
}
