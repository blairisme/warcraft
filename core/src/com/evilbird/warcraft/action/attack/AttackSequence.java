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
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.death.DeathAction;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.move.MoveToItemSequence;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ACTION_COMPLETE;
import static com.evilbird.engine.action.ActionConstants.ACTION_INCOMPLETE;
import static com.evilbird.engine.item.utility.ItemOperations.assignIfAbsent;
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
    private static final int MOVE_ACTION = 0;
    private static final int MELEE_ACTION = 1;
    private static final int RANGED_ACTION = 2;
    private static final int DEATH_ACTION = 3;

    private transient Action current;

    @Inject
    public AttackSequence(MoveToItemSequence move, MeleeAttack melee, RangedAttack ranged, DeathAction death) {
        super(move, melee, ranged, death);
    }

    @Override
    public boolean act(float time) {
        Combatant attacker = (Combatant)getItem();
        Destroyable target = (Destroyable)getTarget();
        return act(time, attacker, target);
    }

    private boolean act(float time, Combatant attacker, Destroyable target) {
        if (! targetValid(attacker, target)) {
            return attackComplete(attacker);
        }
        if (! targetInRange(attacker, target)) {
            return moveInRange(time);
        }
        if (attackTarget(time, attacker)) {
            return killTarget(target);
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

    private boolean attackComplete(Combatant combatant) {
        combatant.setAnimation(UnitAnimation.Idle);
        return ACTION_COMPLETE;
    }

    private boolean moveInRange(float time) {
        if (!subjectMoving()) {
            current = get(MOVE_ACTION);
        }
        return current.act(time) && current.hasError();
    }

    private boolean attackTarget(float time, Combatant attacker) {
        if (! subjectAttacking()) {
            current = isRanged(attacker) ? get(RANGED_ACTION) : get(MELEE_ACTION);
        }
        return current.act(time);
    }

    private boolean killTarget(Destroyable target) {
        Action action = get(DEATH_ACTION);
        assignIfAbsent(target, action);
        return ACTION_COMPLETE;
    }

    private boolean subjectMoving() {
        return current instanceof MoveToItemAction;
    }

    private boolean subjectAttacking() {
        return current instanceof RangedAttack || current instanceof MeleeAttack;
    }
}
