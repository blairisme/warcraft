/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.graphics.animation;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import java.util.List;

/**
 * Represents a sequence of images displayed, displayed in order and separated
 * by a given interval.
 *
 * @author Blair Butterworth
 */
public interface Animation
{
    /**
     * Returns a copy of the Animation that can be used independently.
     *
     * @return a new Animation
     */
    Animation copy();

    /**
     * Returns the length of the animation, specified in seconds.
     *
     * @return the number of seconds in the animation.
     */
    float getDuration();

    /**
     * Returns the next image of the animation, based on the given time. This
     * is the amount of seconds an object has spent in the state this Animation
     * instance represents.
     *
     * @param time  a number of seconds. Must be a positive value.
     * @return      a {@link Drawable} representing a frame of the
     *              animation.
     */
    AnimationFrame getFrame(float time);

    /**
     * Returns the sequence of images displayed in the Animation.
     *
     * @return a {@link List} of {@link Drawable Drawables}.
     */
    List<AnimationFrame> getFrames();

    /**
     * Returns whether the animation, once complete, will start from the
     * beginning again.
     *
     * @return  {@code true} if the animation will loop, otherwise
     *          {@code false}.
     */
    boolean getLooping();

    /**
     * Returns the size of the animation, specified in pixels. Each frame in
     * the animation will conform to this size.
     *
     * @return a {@link GridPoint2}.
     */
    GridPoint2 getSize();

    /**
     * Determines whether the animation would be finished if played without
     * looping, given the time.
     */
    boolean isFinished(float time);
}
