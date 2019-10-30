/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.query.UnitOperations.moveAdjacent;

/**
 * A spell that creates an creature, an eye of kilrogg, that can move about the
 * world acting as a scout. The creature is temporary and will be disappear
 * after a period of time.
 *
 * @author Blair Butterworth
 */
public class EyeOfKilroggSpell extends CreatureSpellAction
{
    @Inject
    public EyeOfKilroggSpell(ItemFactory factory, CreateEvents events, CreatureSpellCancel cancel) {
        super(Spell.EyeOfKilrogg, EffectType.Spell, UnitType.EyeOfKilrogg, factory, events, cancel);
    }

    @Override
    protected Combatant addCreature() {
        Combatant creature = super.addCreature();
        moveAdjacent(creature, getItem());
        return creature;
    }
}
