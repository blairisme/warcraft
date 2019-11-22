/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.common.capability;

/**
 * Defines options for specifying terrain varieties.
 *
 * @author Blair Butterworth
 */
public enum TerrainType
{
    /**
     * Used to specify no terrain.
     */
    None,

    /**
     * Used to specify land terrain, including the shore line.
     */
    Land,

    /**
     * Used to specify water terrain.
     */
    Water,

    /**
     * Used to specify water terrain, including the shore line.
     */
    ShallowWater,

    /**
     * Used to specify air terrain.
     */
    Air
}
