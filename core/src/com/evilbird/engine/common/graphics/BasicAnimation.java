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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import org.apache.commons.lang3.Range;

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
    private float duration;
    private PlayMode mode;
    private Animation animation;
    private Map<Range<Float>, Array<TextureRegion>> frames;

    public BasicAnimation(float direction, float duration, Map<Range<Float>, Array<TextureRegion>> frames, PlayMode mode) {
        this.direction = direction;
        this.duration = duration;
        this.mode = mode;
        this.frames = frames;
        reset();
    }

    public Map<Range<Float>, Array<TextureRegion>> getFrames() {
        return frames;
    }

    @Override
    public TextureRegion getFrame(float time) {
        return animation.getKeyFrame(time);
    }

    public float getDirection() {
        return direction;
    }

    public float getDuration() {
        return duration;
    }

    public PlayMode getMode() {
        return mode;
    }

    @Override
    public void setDirection(float direction) {
        this.direction = direction;
        reset();
    }

    private void reset() {
        this.animation = new Animation(duration, getFrames(direction), mode);
    }

    private Array<TextureRegion> getFrames(float direction) {
        for (Entry<Range<Float>, Array<TextureRegion>> frameEntry : frames.entrySet()) {
            if (frameEntry.getKey().contains(direction)) {
                return frameEntry.getValue();
            }
        }
        return new Array<>();
    }
}
