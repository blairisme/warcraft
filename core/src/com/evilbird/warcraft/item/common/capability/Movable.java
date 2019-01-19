/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.capability;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.lang.Categorizable;
import com.evilbird.engine.common.lang.Positionable;

/**
 * Implementors of this interface provide methods that define a movable object,
 * an item that can navigate around the game world.
 *
 * @author Blair Butterworth
 */
public interface Movable extends Positionable, Categorizable
{
    /**
     * Return the speed of the movable.
     *
     * @return  the the speed of the movable, in pixels per second.
     */
    float getMovementSpeed();

    /**
     * Returns the size that movables displacement, the area in which only this
     * movable can occupy.
     *
     * @return  the size of the movable, in pixels.
     */
    Vector2 getMovementDisplacement();


}
