/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * An {@link Action} that causes a given {@link OffensiveObject combatant} to
 * attack a {@link PerishableObject} using explosives, killing itself in the
 * process.
 *
 * @author Blair Butterworth
 */
public class DemolitionAttack extends BasicAction
{
    private AttackDamage damage;
    private AttackEvents events;

    @Inject
    public DemolitionAttack(AttackDamage damage, AttackEvents events) {
        this.damage = damage;
        this.events = events;
    }

    @Override
    public boolean act(float delta) {
        OffensiveObject attacker = (OffensiveObject)getSubject();
        PerishableObject target = (PerishableObject)getTarget();

        damage.apply(attacker, target);
        damage.applyFull(attacker);

        events.attack(attacker, target);
        return ActionComplete;
    }
}
