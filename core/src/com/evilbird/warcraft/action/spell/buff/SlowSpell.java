/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.common.value.ValueProperty;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collection;

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
    public SlowSpell(ItemFactory factory, SlowCancel cancel) {
        super(Spell.Slow, EffectType.Spell, factory, cancel);
    }

    @Override
    protected Collection<ValueProperty> buffedProperties(Combatant target) {
        Gatherer gatherer = (Gatherer)target;
        return Arrays.asList(
            gatherer.getAttackSpeedProperty(),
            gatherer.getMovementSpeedProperty(),
            gatherer.getGoldGatherSpeedProperty(),
            gatherer.getOilGatherSpeedProperty(),
            gatherer.getWoodGatherSpeedProperty());
    }
}
