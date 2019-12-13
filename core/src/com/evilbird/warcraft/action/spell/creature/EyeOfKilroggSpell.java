/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;

import static com.evilbird.warcraft.object.common.query.UnitOperations.moveAdjacent;

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
    public EyeOfKilroggSpell(GameObjectFactory factory, CreateEvents events, CreatureSpellCancel cancel) {
        super(Spell.EyeOfKilrogg, EffectType.Spell, UnitType.EyeOfKilrogg, factory, events, cancel);
    }

    @Override
    protected GameObject addCreature() {
        GameObject creature = super.addCreature();
        moveAdjacent((MovableObject)creature, getSubject());
        return creature;
    }
}
