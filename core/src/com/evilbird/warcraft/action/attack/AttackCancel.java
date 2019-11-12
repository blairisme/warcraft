/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.item.common.capability.PerishableObject;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Instances of this class stop a {@link Combatant} from attacking.
 *
 * @author Blair Butterworth
 */
public class AttackCancel extends BasicAction
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
    public void setItem(GameObject gameObject) {
        Validate.isInstanceOf(Combatant.class, gameObject);
        super.setItem(gameObject);
    }

    @Override
    public void setTarget(GameObject target) {
        Validate.isAssignableFrom(PerishableObject.class, target.getClass());
        super.setTarget(target);
    }
}
