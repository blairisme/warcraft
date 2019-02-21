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
import com.evilbird.engine.common.lang.Directionable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Positionable;

import java.util.Collection;

/**
 * Implementors of this interface provide methods that define a movable object,
 * an item that can navigate around the game world.
 *
 * @author Blair Butterworth
 */
//TODO: Move into common
public interface Movable extends Positionable, Directionable, Categorizable
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

    /**
     * Returns a collection of item types that the movable can traverse. An
     * empty collection indicates that all item types can be traversed.
     *
     * @return a collection of item type identifiers.
     */
    Collection<Identifier> getMovementCapability();
}
