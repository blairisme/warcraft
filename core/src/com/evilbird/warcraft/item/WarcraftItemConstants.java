/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item;

import com.badlogic.gdx.math.GridPoint2;

/**
 * Defines constants used by Warcraft game items.
 *
 * @author Blair Butterworth
 */
public class WarcraftItemConstants
{
    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;
    public static GridPoint2 TILE_SIZE = new GridPoint2(TILE_WIDTH, TILE_HEIGHT);

    private WarcraftItemConstants() {
    }

    public static int tiles(float count) {
        return Math.round(count * TILE_WIDTH);
    }
}
