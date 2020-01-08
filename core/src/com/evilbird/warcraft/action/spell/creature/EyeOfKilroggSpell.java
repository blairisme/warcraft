/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.object.common.capability.MovableObject;
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
        super(factory, events, cancel);
        setSpell(Spell.EyeOfKilrogg);
        setEffect(EffectType.Spell);
        setProduct(UnitType.EyeOfKilrogg);
    }

    @Override
    protected GameObject newCreature(GameObject caster, GameObject target) {
        GameObject creature = super.newCreature(caster, target);
        moveAdjacent((MovableObject)creature, caster);
        return creature;
    }
}
