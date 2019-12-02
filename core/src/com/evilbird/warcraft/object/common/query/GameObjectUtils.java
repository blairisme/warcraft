/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.common.query;

/**
 * Defines constants used by Warcraft game objects.
 *
 * @author Blair Butterworth
 */
public class GameObjectUtils
{
    /**
     * Disable construction of this static helper class.
     */
    private GameObjectUtils() {
    }

    /**
     * Returns the number of pixels in the given number of tiles.
     */
    public static int tiles(float count) {
        return Math.round(count * 32);
    }
}
