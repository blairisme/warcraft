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

/**
 * Implementors of this interface represent an object that occupies a position
 * in the game space.
 *
 * @author Blair Butterworth
 */
public interface Positionable
{
    /**
     * Returns the current position of the positionable.
     *
     * @return a {@link Vector2 position}.
     */
    Vector2 getPosition();

    /**
     * Sets the position of the positionable.
     *
     * @param position a {@link Vector2 position}.
     */
    void setPosition(Vector2 position);
}
