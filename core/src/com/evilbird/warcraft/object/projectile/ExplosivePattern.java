/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.projectile;

/**
 * Defines options for explosive patterns.
 *
 * @author Blair Butterworth
 */
public enum ExplosivePattern
{
    /**
     * Specifies that an explosion will consist of a sequence of sub-explosions
     * that occur in a line along a projectiles flight path.
     */
    LinearSequence,

    /**
     * Specifies that an explosion will take place in a single point at the end
     * of a projectiles flight path.
     */
    Point
}
