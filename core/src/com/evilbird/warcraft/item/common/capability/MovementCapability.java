/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.capability;

/**
 * Defines options for specifying movement capability.
 *
 * @author Blair Butterworth
 */
public enum MovementCapability
{
    /**
     * Specifies that no movement is allowed.
     */
    None,

    /**
     * Specifies that movement is allowed over land and sea shores.
     */
    Land,

    /**
     * Specifies that movement is allowed over water only.
     */
    Water,

    /**
     * Specifies that movement is allowed over sea and on sea shores.
     */
    ShallowWater,

    /**
     * Specifies that movement is allowed over all terrain types.
     */
    Air
}
