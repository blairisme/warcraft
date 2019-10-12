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
import static com.evilbird.warcraft.item.common.query.UnitOperations.isAlive;

/**
 * Instances of this {@link Action} cause a given {@link Item} to attack
 * another, after first moving within attack range.
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
        if (! targetValid(attacker, target)) {
            return attackInvalid(attacker, target);
        }
        if (moveRequired(attacker, target)) {
            return moveAttacker(time, attacker, target);
        }
        if (attackTarget(time, attacker, target)) {
            return killTarget(attacker, target);
        }
        return ActionIncomplete;
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

    protected boolean targetValid(OffensiveObject attacker, PerishableObject target) {
        return isAlive(attacker) && isAlive(target) && target.getVisible();
    }

    protected boolean attackInvalid(OffensiveObject attacker, PerishableObject target) {
        resetAttacker(attacker);
        events.attackFailed(attacker, target);
        return ActionComplete;
    }

    //TODO: Don't range check on each update
    protected boolean moveRequired(OffensiveObject attacker, PerishableObject target) {
       return current == move || !inRange(attacker, target);
    }

    protected boolean moveAttacker(float time, OffensiveObject attacker, PerishableObject target) {
        if (current != move) {
            stopAttacking(attacker, target);
            current = move;
            current.restart();
        }
//        return current.act(time);
        return performCurrentAction(time);
    }

    protected boolean attackTarget(float time, OffensiveObject attacker, PerishableObject target) {
        if (current != attack) {
            events.attackStarted(attacker, target);
            current = attack;
            current.restart();
        }
        return current.act(time);
    }

//    //TODO: Remove
    private boolean performCurrentAction(float time) {
        if (current.act(time)) {
            if (current.hasError()) {
                return ActionComplete;
            }
            current = null;
        }
        return ActionIncomplete;
    }

    protected boolean killTarget(OffensiveObject attacker, PerishableObject target) {
        assignIfAbsent(target, death);
        events.attackComplete(attacker, target);
        return ActionComplete;
    }

    protected void stopAttacking(OffensiveObject attacker, PerishableObject target) {
        if (current == attack) {
            resetAttacker(attacker);
            events.attackStopped(attacker, target);
        }
    }

    protected void resetAttacker(OffensiveObject attacker) {
        attacker.setAnimation(UnitAnimation.Idle);
    }
}
