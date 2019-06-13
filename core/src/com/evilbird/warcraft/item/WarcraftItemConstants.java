/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item;

/**
 * Defines constants used by Warcraft game items.
 *
 * @author Blair Butterworth
 */
public class WarcraftItemConstants
{
    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;

    private WarcraftItemConstants() {
    }

    public static int tiles(float count) {
        return Math.round(count * TILE_WIDTH);
    }
}
