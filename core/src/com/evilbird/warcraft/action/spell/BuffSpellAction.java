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
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

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

    protected void addAttackSpeedBuff() {
        Combatant target = (Combatant)getTarget();
        Value oldValue = target.getAttackSpeedValue();
        Value newValue = nonStackingBuff(spell, oldValue);
        target.setAttackSpeed(newValue);
    }

    protected void addAttackDamageBuff() {
        Combatant target = (Combatant)getTarget();
        Value oldValue = target.getBasicDamageValue();
        Value newValue = nonStackingBuff(spell, oldValue);
        target.setBasicDamage(newValue);
    }

    protected void addMovementSpeedBuff() {
        Combatant target = (Combatant)getTarget();
        Value oldValue = target.getMovementSpeedValue();
        Value newValue = nonStackingBuff(spell, oldValue);
        target.setMovementSpeed(newValue);
    }

    protected void addGatherSpeedBuff() {
        addGatherGoldSpeedBuff();
        addGatherOilSpeedBuff();
        addGatherWoodSpeedBuff();
    }

    private void addGatherGoldSpeedBuff() {
        Gatherer target = (Gatherer)getTarget();
        Value oldValue = target.getGoldGatherSpeedValue();
        Value newValue = nonStackingBuff(spell, oldValue);
        target.setGoldGatherSpeed(newValue);
    }

    private void addGatherOilSpeedBuff() {
        Gatherer target = (Gatherer)getTarget();
        Value oldValue = target.getOilGatherSpeedValue();
        Value newValue = nonStackingBuff(spell, oldValue);
        target.setOilGatherSpeed(newValue);
    }

    private void addGatherWoodSpeedBuff() {
        Gatherer target = (Gatherer)getTarget();
        Value oldValue = target.getWoodGatherSpeedValue();
        Value newValue = nonStackingBuff(spell, oldValue);
        target.setWoodGatherSpeed(newValue);
    }

    protected Value nonStackingBuff(Spell spell, Value base) {
        if (!(base instanceof BuffValue)) {
            return new BuffValue(spell.getValue(), base);
        }
        return base;
    }
}
