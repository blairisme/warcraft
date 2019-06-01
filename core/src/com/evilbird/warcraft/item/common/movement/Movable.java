/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.movement;

import com.evilbird.engine.item.Item;

/**
 * Implementors of this interface provide methods that define a movable object,
 * an item that can showMenu around the game world.
 *
 * @author Blair Butterworth
 */
public interface Movable extends Item
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
