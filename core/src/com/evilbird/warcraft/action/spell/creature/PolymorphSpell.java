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
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.object.unit.Unit;

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
    public PolymorphSpell(CreateEvents events, GameObjectFactory factory, ItemExclusion exclusion) {
        super(factory, events, null);
        this.exclusion = exclusion;
    }

    @Override
    protected void initialize() {
        super.initialize();

        Unit target = (Unit)getTarget();
        exclusion.disable(target);

        GameObjectGroup parent = target.getParent();
        parent.removeObject(target);
    }

    @Override
    protected GameObject newCreature(GameObject caster, GameObject target) {
        GameObject creature = super.newCreature(caster, target);
        creature.setPosition(target.getPosition());
        return creature;
    }

    @Override
    protected GameObjectGroup getParent(GameObject caster, GameObject target) {
        return target.getParent();
    }
}
