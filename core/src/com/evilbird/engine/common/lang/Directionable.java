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
 * Implementors of this interface represent an object that faces a direction.
 *
 * @author Blair Butterworth
 */
public interface Directionable
{
    /**
     * Sets the direction of the object.
     *
     * @param normalizedDirection   a normalised direction vector.
     */
    void setDirection(Vector2 normalizedDirection);
}
