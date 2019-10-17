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
import com.evilbird.engine.action.framework.CompositeAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.state.OffensiveObject;
import com.evilbird.warcraft.item.common.state.PerishableObject;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import org.apache.commons.lang3.Validate;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.engine.item.utility.ItemOperations.assignIfAbsent;
import static com.evilbird.warcraft.item.common.query.UnitOperations.inRange;

/**
 * An {@link Action} that causes a given {@link OffensiveObject} to attack a
 * {@link PerishableObject}, after first moving within attack range, if
 * applicable.
 *
 * @author Blair Butterworth
 */
public abstract class AttackSequence extends CompositeAction
{
    private transient Action move;
    private transient Action attack;
    private transient Action death;
    private transient Action current;
    private transient AttackEvents events;

    public AttackSequence(AttackEvents events, Action move, Action attack, Action death) {
        super(move, attack, death);
        this.events = events;
        this.move = move;
        this.attack = attack;
        this.death = death;
    }

    @Override
    public boolean act(float time) {
        OffensiveObject attacker = (OffensiveObject)getItem();
        PerishableObject target = (PerishableObject)getTarget();
        return act(time, attacker, target);
    }

    private boolean act(float time, OffensiveObject attacker, PerishableObject target) {
        if (targetInvalid(attacker, target)) {
            return attackFailed(attacker, target);
        }
        if (moveRequired(attacker, target)) {
            return moveAttacker(time, attacker, target);
        }
        if (attackRequired(attacker, target)) {
            return attackTarget(time, attacker, target);
        }
        if (killRequired(attacker, target)) {
            return killTarget(attacker, target);
        }
        return ActionComplete;
    }

    @Override
    public void setItem(Item item) {
        Validate.isInstanceOf(OffensiveObject.class, item);
        super.setItem(item);
    }

    @Override
    public void setTarget(Item target) {
        Validate.isInstanceOf(PerishableObject.class, target);
        super.setTarget(target);
    }

    protected boolean targetInvalid(OffensiveObject attacker, PerishableObject target) {
        return !target.getTouchable() || !target.getVisible();
    }

    protected boolean attackFailed(OffensiveObject attacker, PerishableObject target) {
        resetAttacker(attacker);
        events.attackFailed(attacker, target);
        return ActionComplete;
    }

    protected boolean moveRequired(OffensiveObject attacker, PerishableObject target) {
       return current == move || !inRange(attacker, target);
    }

    protected boolean moveAttacker(float time, OffensiveObject attacker, PerishableObject target) {
        if (current == attack) {
            events.attackStopped(attacker, target);
        }
        if (current != move) {
            current = move;
            current.restart();
            current.setError(null);
        }
        if (current.act(time)) {
            current = null;
            resetAttacker(attacker);
        }
        return move.hasError() ? ActionComplete : ActionIncomplete;
    }

    protected boolean attackRequired(OffensiveObject attacker, PerishableObject target) {
        return target.getHealth() > 0;
    }

    protected boolean attackTarget(float time, OffensiveObject attacker, PerishableObject target) {
        if (current != attack) {
            events.attackStarted(attacker, target);
            current = attack;
            current.restart();
            current.setError(null);
        }
        if (current.act(time)) {
            current = null;
            resetAttacker(attacker);
            events.attackFinished(attacker, target, attack.hasError());
        }
        return attack.hasError() ? ActionComplete : ActionIncomplete;
    }

    protected boolean killRequired(OffensiveObject attacker, PerishableObject target) {
        return target.getHealth() == 0;
    }

    protected boolean killTarget(OffensiveObject attacker, PerishableObject target) {
        resetAttacker(attacker);
        assignIfAbsent(target, death);
        return ActionComplete;
    }

    protected void resetAttacker(OffensiveObject attacker) {
        attacker.setAnimation(UnitAnimation.Idle);
    }
}
