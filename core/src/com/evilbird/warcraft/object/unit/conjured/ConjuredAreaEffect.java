/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.conjured;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.warcraft.object.unit.UnitSound;
import com.evilbird.warcraft.object.unit.UnitStyle;

/**
 * Represents a magically conjured game object that has offensive capabilities
 * effecting multiple targets in a given area.
 *
 * @author Blair Butterworth
 */
public class ConjuredAreaEffect extends ConjuredObject
{
    private static final transient int SOUND_EFFECT_INTERVAL = 5;

    private transient GameTimer timer;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * an {@link UnitStyle}, specifying the visual and auditory
     * presentation of the conjured object.
     *
     * @param skin          a {@code Skin} instance containing a
     *                      {@code UnitStyle}.
     *
     * @throws NullPointerException if the given skin is {@code null} or
     *                              doesn't contain a {@code UnitStyle}.
     */
    public ConjuredAreaEffect(Skin skin) {
        super(skin);
        this.timer = new GameTimer(SOUND_EFFECT_INTERVAL);
        this.timer.end();
    }

    @Override
    public void update(float time) {
        super.update(time);

        if (timer.advance(time)) {
            timer.reset();
            setSound(UnitSound.Background);
        }
    }
}
