/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics.animation;

import com.badlogic.gdx.math.Rectangle;
import org.apache.commons.lang3.Range;

import java.util.List;
import java.util.Map;

/**
 * Specifies the layout of animation frames, their position and dimension,
 * within a texture.
 *
 * @author Blair Butterworth
 */
public class AnimationLayout
{
    private float interval;
    private boolean loop;
    private Map<Range<Float>, List<Rectangle>> frames;

    public AnimationLayout(Map<Range<Float>, List<Rectangle>> frames, float interval, boolean loop) {
        this.frames = frames;
        this.interval = interval;
        this.loop = loop;
    }

    public float getInterval() {
        return interval;
    }

    public boolean getLoop() {
        return loop;
    }

    public Map<Range<Float>, List<Rectangle>> getFrameRegions() {
        return frames;
    }
}
