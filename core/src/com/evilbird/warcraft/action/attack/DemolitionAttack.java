/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
 * attack a {@link PerishableObject} using explosives, killing
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
