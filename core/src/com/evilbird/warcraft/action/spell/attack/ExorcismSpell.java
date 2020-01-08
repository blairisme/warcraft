/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.attack;

import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.object.utility.GameObjectOperations.assignIfAbsent;
import static com.evilbird.warcraft.data.spell.Spell.Exorcism;

/**
 * A spell that degrades the health of a given game object. Damage is dealt
 * instantaneously.
 *
 * @author Blair Butterworth
 */
public class ExorcismSpell extends SpellAction
{
    private transient DeathAction death;

    @Inject
    public ExorcismSpell(GameObjectFactory factory, DeathAction death) {
        super(Exorcism, EffectType.Exorcism, factory);
        this.death = death;
    }

    @Override
    protected void initialize() {
        super.initialize();

        Unit target = (Unit)getTarget();
        target.setHealth(Math.max(0, target.getHealth() - Exorcism.getEffectValue()));

        if (target.getHealth() == 0) {
            assignIfAbsent(target, death);
        }
    }
}
