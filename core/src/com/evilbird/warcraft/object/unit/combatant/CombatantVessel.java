/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.warcraft.object.unit.UnitStyle;

/**
 * A {@link Combatant} specialization representing a craft, either floating or
 * flying. Instances of this class manipulate the combatants animation,
 * periodically offsetting it a few pixel higher or lower to produce a
 * "bobbing" effect, as if they were suspended in a medium, such as air or
 * water.
 *
 * @author Blair Butterworth
 */
public class CombatantVessel extends RangedCombatant
{
    private static transient final float INTERVAL = 1f;
    private static transient final float OFFSET = 2f;

    private transient GameTimer timer;

    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin  a {@link Skin} instance containing, amongst others, a
     *              {@link UnitStyle}.
     */
    public CombatantVessel(Skin skin) {
        super(skin);
        timer = new GameTimer(INTERVAL);
    }

    @Override
    public void update(float time) {
        super.update(time);
        if (timer.advance(time)) {
            timer.reset();
            animation.setOffset(animation.getOffset() == 0 ? OFFSET : 0);
        }
    }
}
