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
import com.evilbird.engine.action.framework.StateTransitionAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import org.apache.commons.lang3.Validate;

import static com.evilbird.warcraft.object.common.query.UnitOperations.inRange;

/**
 * An {@link Action} that causes a given {@link OffensiveObject} to attack a
 * {@link PerishableObject}, after first moving within attack range, if
 * applicable.
 *
 * @author Blair Butterworth
 */
public abstract class AttackSequence extends StateTransitionAction
{
    private transient Action move;
    private transient Action attack;

    public AttackSequence(Action move, Action attack) {
        super(move, attack);
        this.move = move;
        this.attack = attack;
    }

    @Override
    public void setSubject(GameObject subject) {
        Validate.isInstanceOf(OffensiveObject.class, subject);
        super.setSubject(subject);
    }

    @Override
    public void setTarget(GameObject target) {
        Validate.isInstanceOf(PerishableObject.class, target);
        super.setTarget(target);
    }

    @Override
    protected Action nextAction(Action previous) {
        OffensiveObject attacker = (OffensiveObject)getSubject();
        PerishableObject target = (PerishableObject)getTarget();
        return nextAction(attacker, target);
    }

    protected Action nextAction(OffensiveObject attacker, PerishableObject target) {
        if (operationInvalid(attacker, target)) {
            return null;
        }
        if (moveRequired(attacker, target)) {
            return move;
        }
        if (attackRequired(attacker, target)) {
            return attack;
        }
        return null;
    }

    protected boolean operationInvalid(OffensiveObject attacker, PerishableObject target) {
        return attacker.isDead() || target.isDead();
    }

    protected boolean moveRequired(OffensiveObject attacker, PerishableObject target) {
       return !inRange(attacker, target);
    }

    protected boolean attackRequired(OffensiveObject attacker, PerishableObject target) {
        return target.isAlive();
    }

    protected boolean isCriticalError(Action action) {
        return action == move;
    }
}
