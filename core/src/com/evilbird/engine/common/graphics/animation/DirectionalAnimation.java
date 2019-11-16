/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics.animation;

import com.badlogic.gdx.utils.Array;
import org.apache.commons.lang3.Range;

import java.util.Map;

/**
 * Represents an {@link Animation} whose direction can be specified, altering
 * the visual appearance of the animation to accordingly.
 *
 * @author Blair Butterworth
 */
public interface DirectionalAnimation extends Animation
{
    /**
     * Returns the current direction the animation is pointing, specified in
     * degrees.
     *
     * @return a positive integer between 0 and 360.
     */
    float getDirection();

    /**
     * Returns a mapping of the sequence of images displayed by the Animation
     * for each supported direction.
     *
     * @return  a collection of Drawables identified by the directions to
     *          which they apply.
     */
    Map<Range<Float>, Array<AnimationFrame>> getFrameRanges();

    /**
     * Sets the direction of the animation, potentially altering its
     * appearance. Direction is specified in degrees.
     *
     * @param direction a direction. Must be a positive integer between 0 and
     *                  360.
     */
    void setDirection(float direction);
}
