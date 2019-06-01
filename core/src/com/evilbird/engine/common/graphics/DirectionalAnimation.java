/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics;

/**
 * Represents an {@link Animation} whose direction can be specified, altering
 * the visual appearance of the animation to accordingly.
 *
 * @author Blair Butterworth
 */
public interface DirectionalAnimation extends Animation
{
    /**
     * Sets the direction of the animation, potentially altering its
     * appearance. Direction is specified in degrees.
     *
     * @param direction a direction. Must be a positive integer between 0 and
     *                  360.
     */
    void setDirection(float direction);
}
