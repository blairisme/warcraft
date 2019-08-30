/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.specialized;

import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.lang.Identifier;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;

/**
 * Defines the visual and auditory presentation of an {@link Viewable}.
 *
 * @author Blair Butterworth
 */
public class ViewableStyle
{
    public Map<Identifier, Animation> animations;
    public Map<Identifier, SoundEffect> sounds;

    public ViewableStyle() {
        animations = emptyMap();
        sounds = emptyMap();
    }

    public ViewableStyle(ViewableStyle style) {
        animations = new HashMap<>(style.animations);
        sounds = new HashMap<>(style.sounds);
    }
}
