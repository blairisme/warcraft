/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
