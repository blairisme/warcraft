/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.effect;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.animation.Animation;
import com.evilbird.engine.object.AnimatedObject;
import com.evilbird.engine.object.AnimatedObjectStyle;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;

/**
 * A game object that represents an effect, a visual entity that conveys the
 * consequences of an action undertaken by a game object or the user. For
 * example, an explosion resulting from a siege unit attacking a building.
 *
 * @author Blair Butterworth
 */
public class Effect extends AnimatedObject
{
    private transient float duration;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * a {@link AnimatedObjectStyle}, specifying the visual and auditory presentation
     * of the effect.
     *
     * @param skin a {@code Skin} instance.
     */
    public Effect(Skin skin) {
        super(skin);
        duration = -1;
    }

    public float getDuration() {
        if (duration == -1) {
            Animation animation = style.animations.get(Idle);
            duration = animation.getDuration();
        }
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}
