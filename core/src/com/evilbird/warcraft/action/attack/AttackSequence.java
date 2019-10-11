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
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.move.MoveWithinRangeAction;
import com.evilbird.warcraft.item.common.state.OffensiveObject;
import com.evilbird.warcraft.item.common.state.PerishableObject;
import com.evilbird.warcraft.item.common.state.RangedOffensiveObject;
import com.evilbird.warcraft.item.projectile.Projectile;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.engine.item.utility.ItemOperations.assignIfAbsent;
import static com.evilbird.warcraft.action.attack.AttackEvents.attackComplete;
import static com.evilbird.warcraft.action.attack.AttackEvents.attackFailed;
import static com.evilbird.warcraft.action.attack.AttackEvents.attackStarted;
import static com.evilbird.warcraft.action.attack.AttackEvents.attackStopped;
import static com.evilbird.warcraft.item.common.query.UnitOperations.inRange;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isAlive;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isRanged;

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
        MoveToItemAction meleeMove,
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

    private boolean targetValid(OffensiveObject attacker, PerishableObject target) {
        return isAlive(attacker) && isAlive(target) && target.getVisible();
    }

    private boolean attackInvalid(OffensiveObject attacker, PerishableObject target) {
        resetAttacker(attacker);
        attackFailed(events, attacker, target);
        return ActionComplete;
    }

    private boolean performCurrentAction(float time) {
        if (current.act(time)) {
            if (current.hasError()) {
                return ActionComplete;
            }
            current = null;
        }
        return ActionIncomplete;
    }

    private void assignMoveAction(OffensiveObject attacker, PerishableObject target) {
        stopAttacking(attacker, target);
        current = isRanged(attacker) ? get(RANGED_MOVE) : get(MELEE_MOVE);
        current.restart();
    }

    private void assignAttackAction(OffensiveObject attacker, PerishableObject target) {
        current = isRanged(attacker) ? get(RANGED_ATTACK) : get(MELEE_ATTACK);
        current.restart();
        attackStarted(events, attacker, target);
    }

    private boolean moveRequired(OffensiveObject attacker, PerishableObject target) {
       return subjectMoving() || !inRange(attacker, target);
    }

    private boolean moveAttacker(float time, OffensiveObject attacker, PerishableObject target) {
        if (!subjectMoving()) {
            assignMoveAction(attacker, target);
        }
        return performCurrentAction(time);
    }

    private boolean attackTarget(float time, OffensiveObject attacker, PerishableObject target) {
        if (! subjectAttacking()) {
            assignAttackAction(attacker, target);
        }
        return current.act(time);
    }

    private boolean killTarget(OffensiveObject attacker, PerishableObject target) {
        assignIfAbsent(target, get(DEATH_ACTION));
        attackComplete(events, attacker, target);
        return ActionComplete;
    }

    private void stopAttacking(OffensiveObject attacker, PerishableObject target) {
        if (subjectAttacking()) {
            resetAttacker(attacker);
            attackStopped(events, attacker, target);
        }
    }

    private void resetAttacker(OffensiveObject attacker) {
        attacker.setAnimation(UnitAnimation.Idle);
        if (attacker instanceof RangedOffensiveObject) {
            RangedOffensiveObject rangedAttacker = (RangedOffensiveObject)attacker;
            Projectile projectile = rangedAttacker.getProjectile();
            if (projectile != null) {
                projectile.setVisible(false);
            }
        }
    }

    private boolean subjectMoving() {
        return current instanceof MoveToItemAction || current instanceof MoveWithinRangeAction;
    }

    private boolean subjectAttacking() {
        return current instanceof RangedAttack || current instanceof MeleeAttack;
    }
}
