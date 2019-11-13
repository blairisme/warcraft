/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.layer;

import com.evilbird.engine.object.GameObjectType;

/**
 * Defines identifiers for layer varieties.
 *
 * @author Blair Butterworth
 */
public enum LayerType implements GameObjectType
{
    Map, //Terrain
    Sea,
    Shore,
    Forest,
    Mountain,
    Tree,
    OpaqueFog,
    OpaqueFogSection,
    TransparentFog,
    Wall,
    WallSection
}
