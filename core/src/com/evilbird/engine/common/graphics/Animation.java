/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Represents a sequence of images displayed in sequence. Each image of the
 * Animation is called a frame and its represented as a {@link TextureRegion}.
 *
 * @author Blair Butterworth
 */
public interface Animation
{
    /**
     * Returns the next image of the animation, based on the given time. This
     * is the amount of seconds an object has spent in the state this Animation
     * instance represents.
     *
     * @param time  a number of seconds. Must be a positive value.
     * @return      a {@link TextureRegion} representing a frame of the
     *              animation.
     */
    TextureRegion getFrame(float time);
}
