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
import com.evilbird.warcraft.item.common.value.BuffValue;
import com.evilbird.warcraft.item.common.value.Value;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

/**
 * A base class for spells that modify the attributes of a given game object.
 *
 * @author Blair Butterworth
 */
public abstract class BuffSpellAction extends SpellAction
{
    public BuffSpellAction(Spell spell, EffectType effect, ItemFactory factory) {
        super(spell, effect, factory);
    }

    protected void addAttackDamageBuff() {
        Combatant target = (Combatant)getTarget();
        Value oldDamage = target.getBasicDamageValue();
        Value newDamage = nonStackingBuff(spell, oldDamage);
        target.setBasicDamage(newDamage);
    }

    protected Value nonStackingBuff(Spell spell, Value base) {
        if (!(base instanceof BuffValue)) {
            return new BuffValue(spell.getValue(), base);
        }
        return base;
    }
}
