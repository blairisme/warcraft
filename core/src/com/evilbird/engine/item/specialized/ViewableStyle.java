/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.specialized;

import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.lang.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.emptyMap;

/**
 * Defines the visual and auditory presentation of an {@link Viewable}.
 *
 * @author Blair Butterworth
 */
public class ViewableStyle
{
    public Map<Identifier, Animation> animations;
    public Map<Identifier, Sound> sounds;

    /**
     * Creates a new instance of this class with no animations or sounds.
     */
    public ViewableStyle() {
        animations = emptyMap();
        sounds = emptyMap();
    }

    /**
     * Creates a new instance of this class with given another style whose
     * attributes will be copied into the new style. Subsequent changes to the
     * new style will not be reflected in the style it was copied from.
     *
     * @param style a {@link ViewableStyle} whose style attributes will be
     *              copied.
     *
     * @throws NullPointerException thrown if the given style is {@code null}.
     */
    public ViewableStyle(ViewableStyle style) {
        Objects.requireNonNull(style);
        animations = new HashMap<>(style.animations);
        sounds = new HashMap<>(style.sounds);
    }
}
