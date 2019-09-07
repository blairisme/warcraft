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
import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ACTION_COMPLETE;
import static com.evilbird.warcraft.action.attack.AttackEvents.attackCancelled;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Instances of this class stop a {@link Combatant} from attacking.
 *
 * @author Blair Butterworth
 */
public class AttackCancel extends BasicAction
{
    private transient Events events;

    @Inject
    public AttackCancel(EventQueue events) {
        this.events = events;
        setIdentifier(AttackActions.AttackCancel);
    }

    @Override
    public boolean act(float delta) {
        Combatant attacker = (Combatant)getItem();
        Destroyable target = (Destroyable)getTarget();

        attacker.setAnimation(Idle);
        attackCancelled(events, attacker, target);

        return ACTION_COMPLETE;
    }

    @Override
    public void setItem(Item item) {
        Validate.isInstanceOf(Combatant.class, item);
        super.setItem(item);
    }

    @Override
    public void setTarget(Item target) {
        Validate.isAssignableFrom(Destroyable.class, target.getClass());
        super.setTarget(target);
    }
}
