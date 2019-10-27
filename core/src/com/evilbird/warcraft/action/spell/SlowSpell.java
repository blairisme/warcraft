/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell;

import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.effect.EffectType;

import javax.inject.Inject;

/**
 * A spell that slows a given targets movement and attack speeds for a period
 * of time. The spell is cast instantaneously and won't be repeated until cast
 * again.
 *
 * @author Blair Butterworth
 */
public class SlowSpell extends BuffSpellAction
{
    @Inject
    public SlowSpell(ItemFactory factory) {
        super(Spell.Slow, EffectType.Spell, factory);
    }

    @Override
    protected void initialize() {
        super.initialize();
        addAttackSpeedBuff();
        addMovementSpeedBuff();
        addGatherSpeedBuff();
    }
}
