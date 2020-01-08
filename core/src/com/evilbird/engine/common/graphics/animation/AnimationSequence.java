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

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link DirectionalAnimation} implementation that displays a sequence of
 * {@link Animation Animations} in order. This class does not support looping.
 *
 * @author Blair Butterworth
 */
public class AnimationSequence implements DirectionalAnimation
{
    private float direction;
    private float duration;
    private GridPoint2 size;
    private List<Animation> animations;
    private List<AnimationFrame> frames;

    /**
     * Constructs a new instance of this class given an ordered sequence of
     * animations whose contents will be displayed by the AnimationSequence.
     */
    public AnimationSequence(List<Animation> animations) {
        this.animations = animations;
        this.direction = 0f;
        this.duration = calculateDuration(animations);
        this.size = calculateSize(animations);
        this.frames = calculateFrames(animations);
    }

    private AnimationSequence(AnimationSequence sequence) {
        this.animations = sequence.animations;
        this.direction = sequence.direction;
        this.duration = sequence.duration;
        this.size = sequence.size;
        this.frames = sequence.frames;
    }

    /**
     * Returns a copy of the Animation that can be used independently.
     *
     * @return a new Animation
     */
    @Override
    public Animation copy() {
        return new AnimationSequence(this);
    }

    /**
     * Returns the current direction the animation is pointing, specified in
     * degrees.
     *
     * @return a positive integer between 0 and 360.
     */
    @Override
    public float getDirection() {
        return direction;
    }

    /**
     * Returns the length of the animation, specified in seconds.
     *
     * @return the number of seconds in the animation.
     */
    @Override
    public float getDuration() {
        return duration;
    }

    /**
     * Returns the next image of the animation, based on the given time. This
     * is the amount of seconds an object has spent in the state this Animation
     * instance represents.
     *
     * @param time  a number of seconds. Must be a positive value.
     * @return      a {@link Drawable} representing a frame of the
     *              animation.
     */
    @Override
    public AnimationFrame getFrame(float time) {
        Animation animation = getCurrentAnimation(time);
        return animation.getFrame(time);
    }

    /**
     * Returns the sequence of images displayed in the Animation.
     *
     * @return a {@link List} of {@link Drawable Drawables}.
     */
    @Override
    public List<AnimationFrame> getFrames() {
        return frames;
    }

    /**
     * Returns {@code false}, as AnimationSequences do not support looping.
     */
    @Override
    public boolean getLooping() {
        return false;
    }

    /**
     * Returns the size of the animation, specified in pixels. Each frame in
     * the animation will conform to this size.
     */
    @Override
    public GridPoint2 getSize() {
        return size;
    }

    /**
     * Determines whether the animation would be finished if played without
     * looping, given the time.
     */
    @Override
    public boolean isFinished(float time) {
        for (Animation animation: animations) {
            if (! animation.isFinished(time)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sets the direction of the animation, potentially altering its
     * appearance. Direction is specified in degrees.
     *
     * @param direction a direction. Must be a positive integer between 0 and
     *                  360.
     */
    @Override
    public void setDirection(float direction) {
        this.direction = direction;
        for (Animation animation: animations) {
            if (animation instanceof DirectionalAnimation) {
                DirectionalAnimation directional = (DirectionalAnimation)animation;
                directional.setDirection(direction);
            }
        }
    }

    private float calculateDuration(List<Animation> animations) {
        float result = 0;
        for (Animation animation: animations) {
            result += animation.getDuration();
        }
        return result;
    }

    private GridPoint2 calculateSize(List<Animation> animations) {
        GridPoint2 result = new GridPoint2(0, 0);
        for (Animation animation: animations) {
            GridPoint2 elementSize = animation.getSize();
            result.x = Math.max(result.x, elementSize.x);
            result.y = Math.max(result.y, elementSize.y);
        }
        return result;
    }

    private List<AnimationFrame> calculateFrames(List<Animation> animations) {
        List<AnimationFrame> result = new ArrayList<>();
        for (Animation animation: animations) {
            result.addAll(animation.getFrames());
        }
        return result;
    }

    private Animation getCurrentAnimation(float time) {
        for (Animation animation: animations) {
            if (! animation.isFinished(time)) {
                return animation;
            }
        }
        return animations.get(animations.size() - 1);
    }
}
