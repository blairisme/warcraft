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
import com.evilbird.engine.action.ActionException;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitSound;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.object.common.query.UnitOperations.inRange;
import static com.evilbird.warcraft.object.common.query.UnitOperations.reorient;

/**
 * Instances of this {@link Action} reduce the health of the Actions target.
 * The Action will end when the targets health reaches zero.
 *
 * @author Blair Butterworth
 */
public class ProximityAttack extends BasicAction
{
    private transient AttackDamage damage;
    private transient AttackEvents events;
    private transient WarcraftPreferences preferences;
    private transient OffensiveObject attacker;
    private transient PerishableObject target;

    @Inject
    public ProximityAttack(
        AttackDamage damage,
        AttackEvents events,
        WarcraftPreferences preferences)
    {
        this.damage = damage;
        this.events = events;
        this.preferences = preferences;
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            initialize();
        }
        if (! operationValid()) {
            return operationFailed();
        }
        if (! readyToAttack()) {
            return delayAttack(time);
        }
        if (attackTarget()) {
            return attackComplete();
        }
        return ActionIncomplete;
    }

    @Override
    public void reset() {
        super.reset();
        attacker = null;
        target = null;
    }

    private boolean initialized() {
        return attacker != null;
    }

    protected void initialize() {
        attacker = (OffensiveObject)getSubject();
        attacker.setAnimation(UnitAnimation.Attack);
        target = (PerishableObject)getTarget();
        events.attackStarted(attacker, target);
    }

    private boolean operationValid() {
        return attacker.isAlive() && target.isAlive() && inRange(attacker, target);
    }

    private boolean operationFailed() {
        events.attackFailed(attacker, target);
        setError(new ActionException("Attack Failed"));
        return ActionComplete;
    }

    private boolean readyToAttack() {
        return attacker.getAttackTime() == 0;
    }

    protected boolean delayAttack(float time) {
        attacker.setAttackTime(Math.max(attacker.getAttackTime() - time, 0));
        return ActionIncomplete;
    }

    protected boolean attackTarget() {
        attacker.setAttackTime(attacker.getAttackSpeed());
        attacker.setSound(UnitSound.Attack, preferences.getEffectsVolume());
        reorient(attacker, target, false);
        damage.apply(attacker, target);
        return target.isDead();
    }

    private boolean attackComplete() {
        attacker.setAnimation(UnitAnimation.Idle);
        events.attackComplete(attacker, target);
        return ActionComplete;
    }
}

