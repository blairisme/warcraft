/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.object.common.spell.Spell;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * A spell that transforms a given target into a critter, rendering it unable
 * to be controlled for a period of time.
 *
 * @author Blair Butterworth
 */
public class PolymorphSpell extends CreatureSpellAction
{
    private ItemExclusion exclusion;

    @Inject
    public PolymorphSpell(
        GameObjectFactory factory,
        ItemExclusion exclusion,
        CreateEvents events,
        PolymorphCancel cancel)
    {
        super(Spell.Polymorph, EffectType.Spell, UnitType.Sheep, factory, events, cancel);
        this.exclusion = exclusion;
    }

    @Override
    protected void initialize() {
        super.initialize();
        exclusion.disable((Unit)getTarget());
    }

    @Override
    protected Combatant addCreature() {
        Combatant target = (Combatant)getTarget();
        Combatant creature = super.addCreature();
        creature.setPosition(target.getPosition());
        creature.setAssociatedItem(target);
        return creature;
    }
}
