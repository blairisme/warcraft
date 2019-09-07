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
import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.death.DeathAction;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.move.MoveToItemSequence;
import com.evilbird.warcraft.action.move.MoveWithinRangeAction;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ACTION_COMPLETE;
import static com.evilbird.engine.action.ActionConstants.ACTION_INCOMPLETE;
import static com.evilbird.engine.item.utility.ItemOperations.assignIfAbsent;
import static com.evilbird.warcraft.action.attack.AttackEvents.attackComplete;
import static com.evilbird.warcraft.action.attack.AttackEvents.attackFailed;
import static com.evilbird.warcraft.action.attack.AttackEvents.attackStarted;
import static com.evilbird.warcraft.action.attack.AttackEvents.attackStopped;
import static com.evilbird.warcraft.item.common.query.UnitOperations.inRange;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isAlive;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isRanged;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isShip;
import static com.evilbird.warcraft.item.common.query.UnitOperations.reorient;

/**
 * Instances of this {@link Action} cause a given {@link Item} to attack
 * another, after first moving within attack range.
 *
 * @author Blair Butterworth
 */
public class AttackSequence extends CompositeAction
{
    private static final int MELEE_MOVE = 0;
    private static final int RANGED_MOVE = 1;
    private static final int MELEE_ATTACK = 2;
    private static final int RANGED_ATTACK = 3;
    private static final int DEATH_ACTION = 4;

    private transient Action current;
    private transient Events events;

    @Inject
    public AttackSequence(
        MoveToItemSequence meleeMove,
        MoveWithinRangeAction rangedMove,
        MeleeAttack meleeAttack,
        RangedAttack rangedAttack,
        DeathAction death,
        EventQueue events)
    {
        super(meleeMove, rangedMove, meleeAttack, rangedAttack, death);
        this.events = events;
    }

    @Override
    public boolean act(float time) {
        Combatant attacker = (Combatant)getItem();
        Destroyable target = (Destroyable)getTarget();
        return act(time, attacker, target);
    }

    private boolean act(float time, Combatant attacker, Destroyable target) {
        if (! targetValid(attacker, target)) {
            return attackInvalid(attacker, target);
        }
        if (! targetInRange(attacker, target)) {
            return moveInRange(time, attacker, target);
        }
        if (attackTarget(time, attacker, target)) {
            return killTarget(attacker, target);
        }
        return ACTION_INCOMPLETE;
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

    private boolean targetValid(Combatant attacker, Destroyable target) {
        return isAlive(attacker) && isAlive(target) && target.getVisible();
    }

    private boolean targetInRange(Combatant attacker, Destroyable target) {
        return !subjectMoving() && inRange(attacker, target);
    }

    private boolean attackInvalid(Combatant attacker, Destroyable target) {
        attacker.setAnimation(UnitAnimation.Idle);
        attackFailed(events, attacker, target);
        return ACTION_COMPLETE;
    }

    private boolean moveInRange(float time, Combatant attacker, Destroyable target) {
        if (!subjectMoving()) {
            current = isRanged(attacker) ? get(RANGED_MOVE) : get(MELEE_MOVE);

            if (subjectAttacking()) {
                attackStopped(events, attacker, target);
            }
        }
        return current.act(time) && current.hasError();
    }

    private boolean attackTarget(float time, Combatant attacker, Destroyable target) {
        if (! subjectAttacking()) {
            current = isRanged(attacker) ? get(RANGED_ATTACK) : get(MELEE_ATTACK);
            attackStarted(events, attacker, target);
            reorient(attacker, target, isShip(attacker));
        }
        return current.act(time);
    }

    private boolean killTarget(Combatant attacker, Destroyable target) {
        assignIfAbsent(target, get(DEATH_ACTION));
        attackComplete(events, attacker, target);
        return ACTION_COMPLETE;
    }

    private boolean subjectMoving() {
        return current instanceof MoveToItemAction;
    }

    private boolean subjectAttacking() {
        return current instanceof RangedAttack || current instanceof MeleeAttack;
    }
}
