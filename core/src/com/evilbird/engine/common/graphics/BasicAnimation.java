/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.collection.CollectionUtils;
import org.apache.commons.lang3.Range;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents an {@link Animation} whose direction can be specified, altering
 * the visual appearance of the animation to accordingly.
 *
 * @author Blair Butterworth
 */
public class BasicAnimation implements DirectionalAnimation
{
    private float direction;
    private float interval;
    private PlayMode mode;
    private GridPoint2 size;
    private Animation<AnimationFrame> animation;
    private Map<Range<Float>, Array<AnimationFrame>> frames;

    /**
     * Creates a new instance of this class whose attributes will be copied
     * from a given animation. The resulting animation can be used without
     * influencing the animation from which it was created.
     *
     * @param another   an animation from which the direction, duration, frames
     *                  and mode will be copied.
     */
    public BasicAnimation(BasicAnimation another) {
        this.direction = another.direction;
        this.interval = another.interval;
        this.mode = another.mode;
        this.size = another.size;
        this.animation = another.animation;
        this.frames = another.frames;
    }

    /**
     * Creates a new instance of this class, given the frames displayed by the
     * animation, the time between frames and whether or not the animation will
     * loop.
     *
     * @param frames    a map containing the sequence of images displayed by
     *                  the Animation for each supported direction.
     * @param interval  the time between frames, specified in seconds.
     * @param looping   whether the animation, once complete, will start from
     *                  the beginning again.
     */
    public BasicAnimation(Map<Range<Float>, Array<AnimationFrame>> frames, float interval, boolean looping) {
        this.direction = 0f;
        this.interval = interval;
        this.mode = looping ? PlayMode.LOOP : PlayMode.NORMAL;
        this.frames = frames;
        reset();
    }

    /**
     * Returns a copy of the Animation that can be used independently.
     *
     * @return a new Animation
     */
    @Override
    public BasicAnimation copy() {
        return new BasicAnimation(this);
    }

    /**
     * Returns the next image of the animation, based on the given time. This
     * is the amount of seconds an object has spent in the state this Animation
     * instance represents.
     *
     * @param time  a number of seconds. Must be a positive value.
     * @return      an {@link AnimationFrame} representing a frame of the
     *              animation.
     */
    @Override
    public AnimationFrame getFrame(float time) {
        return animation.getKeyFrame(time);
    }

    /**
     * Returns the sequence of images displayed in the Animation.
     *
     * @return a {@link List} of {@link Drawable Drawables}.
     */
    public List<AnimationFrame> getFrames() {
        return CollectionUtils.flatten(frames.values());
    }

    /**
     * Returns a mapping of the sequence of images displayed by the Animation
     * for each supported direction.
     *
     * @return  a collection of Drawables identified by the directions to
     *          which they apply.
     */
    public Map<Range<Float>, Array<AnimationFrame>> getFrameRanges() {
        return frames;
    }

    /**
     * Returns the size of the animation, specified in pixels. Each frame in
     * the animation will conform to this size.
     *
     * @return a {@link GridPoint2}.
     */
    @Override
    public GridPoint2 getSize() {
        return size;
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
     * Returns the interval between frames, specified in seconds.
     *
     * @return the number of seconds between frames.
     */
    public float getInterval() {
        return interval;
    }

    /**
     * Returns whether the animation, once complete, will start from the
     * beginning again.
     *
     * @return  {@code true} if the animation will loop, otherwise
     *          {@code false}.
     */
    public boolean getLooping() {
        return mode == PlayMode.LOOP;
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
        reset();
    }

    private void reset() {
        this.animation = new Animation<>(interval, getFrameSequence(direction), mode);
        this.size = getFrameSize(animation);
    }

    private Array<AnimationFrame> getFrameSequence(float direction) {
        for (Entry<Range<Float>, Array<AnimationFrame>> frameEntry : frames.entrySet()) {
            if (frameEntry.getKey().contains(direction)) {
                return frameEntry.getValue();
            }
        }
        return new Array<>();
    }

    private GridPoint2 getFrameSize(Animation<AnimationFrame> animation) {
        Drawable region = animation.getKeyFrame(0);
        return new GridPoint2((int)region.getMinWidth(), (int)region.getMinHeight());
    }
}
