/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.lang.Categorizable;
import com.evilbird.engine.common.lang.Directionable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Positionable;

import java.util.Collection;

/**
 * Implementors of this interface provide methods that define a movable object,
 * an item that can showMenu around the game world.
 *
 * @author Blair Butterworth
 */
public interface Movable extends Positionable, Directionable, Categorizable
{
    /**
     * Return the speed of the movable.
     *
     * @return  the the speed of the movable, in pixels per second.
     */
    float getMovementSpeed();

    /**
     * Returns a collection of item types that the movable can traverse. An
     * empty collection indicates that all item types can be traversed.
     *
     * @return a collection of item type identifiers.
     */
    Collection<Identifier> getMovementCapability();
}
