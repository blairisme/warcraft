/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer;

import com.evilbird.engine.item.ItemType;

/**
 * Defines identifiers for layer varieties.
 *
 * @author Blair Butterworth
 */
public enum LayerType implements ItemType
{
    Map, //Terrain
    Sea,
    Shore,
    Forest,
    Tree,
    OpaqueFog,
    OpaqueFogSection,
    TransparentFog,
    Wall,
    WallSection
}
