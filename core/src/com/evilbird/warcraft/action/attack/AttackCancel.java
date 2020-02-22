/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.framework.AbstractAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;

/**
 * Instances of this class stop a {@link Combatant} from attacking.
 *
 * @author Blair Butterworth
 */
public class AttackCancel extends AbstractAction
{
    private transient AttackEvents events;

    @Inject
    public AttackCancel(AttackEvents events) {
        this.events = events;
        setIdentifier(AttackActions.AttackCancel);
    }

    @Override
    public boolean act(float delta) {
        Combatant attacker = (Combatant) getSubject();
        PerishableObject target = (PerishableObject)getTarget();

        attacker.setAnimation(Idle);
        events.attackCancelled(attacker, target);

        return ActionComplete;
    }

    @Override
    public void setSubject(GameObject gameObject) {
        Validate.isInstanceOf(Combatant.class, gameObject);
        super.setSubject(gameObject);
    }

    @Override
    public void setTarget(GameObject target) {
        Validate.isAssignableFrom(PerishableObject.class, target.getClass());
        super.setTarget(target);
    }
}
