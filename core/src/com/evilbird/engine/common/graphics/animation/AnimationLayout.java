/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
