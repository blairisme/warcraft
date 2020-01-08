/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
