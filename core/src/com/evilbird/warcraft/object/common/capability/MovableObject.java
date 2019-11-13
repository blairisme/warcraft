/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
     * Return the speed of the movable.
     *
     * @return  the the speed of the movable, in pixels per second.
     */
    int getMovementSpeed();

    /**
     * Returns a {@link MovementCapability} defining which items the movable
     * can traverse over.
     *
     * @return a MovementCapability.
     */
    MovementCapability getMovementCapability();
}
