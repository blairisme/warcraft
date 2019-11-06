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

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

/**
 * A spell that increases the attack damage of a given combatant. The spell is
 * cast instantaneously and its effects wear of after a period of time.
 *
 * @author Blair Butterworth
 */

public class BloodlustSpell extends BuffSpellAction
{
    @Inject
    public BloodlustSpell(ItemFactory factory, BloodlustCancel cancel) {
        super(Spell.Bloodlust, EffectType.Spell, factory, cancel);
    }

    @Override
    protected Collection<ValueProperty> buffedProperties(Combatant target) {
        return Collections.singletonList(target.getAttackSpeedProperty());
    }
}
