/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.action.spell.creature.CreatureSpellAction;
import com.evilbird.warcraft.action.spell.creature.CreatureSpellCancel;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * A spell that creates a magical explosive trap that explodes when any type of
 * game object approaches it. The trap will eventually exploded it unused.
 *
 * @author Blair Butterworth
 */
public class RunesSpell extends CreatureSpellAction
{
    @Inject
    public RunesSpell(ItemFactory factory, CreateEvents events, CreatureSpellCancel cancel) {
        super(Spell.Runes, EffectType.Rune, UnitType.RuneTrap, factory, events, cancel);
    }

    @Override
    protected Combatant addCreature() {
        Combatant creature = super.addCreature();
        creature.setPosition(getTarget().getPosition());
        return creature;
    }
}
