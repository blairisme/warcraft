/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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