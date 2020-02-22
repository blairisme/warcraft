/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.AbstractAction;
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
public class ProximityAttack extends AbstractAction
{
    private transient AttackDamage damage;
    private transient AttackEvents events;
    private transient OffensiveObject attacker;
    private transient PerishableObject target;

    @Inject
    public ProximityAttack(AttackDamage damage, AttackEvents events) {
        this.damage = damage;
        this.events = events;
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
        reorient(attacker, target, false);
    }

    private boolean operationValid() {
        return attacker.isAlive() && target.isAlive() && inRange(attacker, target);
    }

    private boolean operationFailed() {
        events.attackFailed(attacker, target);
        setFailed("Attack Failed");
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
        attacker.setSound(UnitSound.Attack);
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

