/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
