/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object;

import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.engine.common.graphics.animation.Animation;
import com.evilbird.engine.common.lang.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.emptyMap;

/**
 * Defines the visual and auditory presentation of an {@link AnimatedObject}.
 *
 * @author Blair Butterworth
 */
public class AnimatedObjectStyle
{
    public Map<Identifier, Animation> animations;
    public Map<Identifier, Sound> sounds;

    /**
     * Creates a new instance of this class with no animations or sounds.
     */
    public AnimatedObjectStyle() {
        animations = emptyMap();
        sounds = emptyMap();
    }

    /**
     * Creates a new instance of this class given another style whose
     * attributes will be copied into the new style. Subsequent changes to the
     * new style will not be reflected in the style it was copied from.
     *
     * @param style a {@link AnimatedObjectStyle} whose style attributes will be
     *              copied.
     *
     * @throws NullPointerException thrown if the given style is {@code null}.
     */
    public AnimatedObjectStyle(AnimatedObjectStyle style) {
        Objects.requireNonNull(style);
        animations = new HashMap<>(style.animations);
        sounds = new HashMap<>(style.sounds);
    }
}
