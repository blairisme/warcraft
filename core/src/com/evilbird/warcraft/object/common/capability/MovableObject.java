/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.common.capability;

import com.evilbird.engine.common.lang.Animated;
import com.evilbird.engine.common.lang.Audible;
import com.evilbird.engine.common.lang.Directionable;
import com.evilbird.engine.object.GameObject;

/**
 * Implementors of this interface provide methods that define a movable object,
 * an item that can be moved around the game world.
 *
 * @author Blair Butterworth
 */
public interface MovableObject extends GameObject, Animated, Audible, Directionable
{
    /**
     * Returns the speed of the movable object, specified in pixels per second.
     */
    int getMovementSpeed();

    /**
     * Returns a {@link TerrainType} defining which types of terrain the
     * movable object can traverse over.
     */
    TerrainType getMovementCapability();
}
