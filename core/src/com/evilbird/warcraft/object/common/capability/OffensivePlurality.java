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
 * A value indicating the number of targets an {@code OffensiveObject} can
 * attack at a time.
 *
 * @author Blair Butterworth
 */
public enum OffensivePlurality
{
    /**
     * Indicates that an offensive object can only attack one target at a time.
     */
    Individual,

    /**
     * Indicates that an offensive object can attack multiple targets at a time.
     */
    Multiple
}