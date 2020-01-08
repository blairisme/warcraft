/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.renderable.OffsetRenderable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.warcraft.object.unit.UnitStyle;
import org.apache.commons.lang3.RandomUtils;

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
    private static final transient float INTERVAL = 1f;
    private static final transient float OFFSET = 3f;

    private transient GameTimer timer;
    private transient float offset;
    private transient OffsetRenderable offsetRenderable;

    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin  a {@link Skin} instance containing, amongst others, a
     *              {@link UnitStyle}.
     */
    public CombatantVessel(Skin skin) {
        super(skin);
        offset = 0f;
        timer = new GameTimer(INTERVAL);
        timer.advance(RandomUtils.nextFloat(0f, INTERVAL));
    }

    @Override
    public void setAnimation(Identifier id, float time) {
        super.setAnimation(id, time);
        this.offsetRenderable = new OffsetRenderable(this.renderable);
        super.renderable = offsetRenderable;
    }

    @Override
    public void update(float time) {
        super.update(time);
        if (isAlive() && timer.advance(time)) {
            timer.reset();
            offset = offset == 0 ? OFFSET : 0;
            offsetRenderable.setOffset(0, offset);
        }
    }
}
