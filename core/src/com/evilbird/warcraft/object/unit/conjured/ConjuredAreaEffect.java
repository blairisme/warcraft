/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.conjured;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.warcraft.common.WarcraftPreferences;
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
    private transient WarcraftPreferences preferences;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * an {@link UnitStyle}, specifying the visual and auditory
     * presentation of the conjured object and a {@link WarcraftPreferences}
     * instance used to set the volume of sound effects played by it.
     *
     * @param skin          a {@code Skin} instance containing a
     *                      {@code UnitStyle}.
     * @param preferences   a {@link WarcraftPreferences} instance used to set
     *                      the volume of sound effects played by the conjured
     *                      object.
     *
     * @throws NullPointerException if the given skin is {@code null} or
     *                              doesn't contain a {@code UnitStyle}.
     */
    public ConjuredAreaEffect(Skin skin, WarcraftPreferences preferences) {
        super(skin);
        this.preferences = preferences;
        this.timer = new GameTimer(SOUND_EFFECT_INTERVAL);
        this.timer.end();
    }

    @Override
    public void update(float time) {
        super.update(time);

        if (timer.advance(time)) {
            timer.reset();
            setSound(UnitSound.Background, preferences.getEffectsVolume());
        }
    }
}
