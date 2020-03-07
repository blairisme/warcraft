/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitSound;

import javax.inject.Inject;

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

    @Inject
    public ProximityAttack(AttackDamage damage, AttackEvents events) {
        this.damage = damage;
        this.events = events;
    }

    @Override
    public void start() {
        super.start();

        OffensiveObject attacker = (OffensiveObject)getSubject();
        PerishableObject target = (PerishableObject)getTarget();

        attacker.setAnimation(UnitAnimation.Attack);
        reorient(attacker, target, false);

        events.attackStarted(attacker, target);
    }

    @Override
    public ActionResult act(float time) {
        OffensiveObject attacker = (OffensiveObject)getSubject();
        PerishableObject target = (PerishableObject)getTarget();

        if (! attackValid(attacker, target)) {
            return ActionResult.Failed;
        }
        if (! attackReady(attacker, time)) {
            return ActionResult.Incomplete;
        }
        if (attackTarget(attacker, target)) {
            return ActionResult.Complete;
        }
        return ActionResult.Incomplete;
    }

    @Override
    public void end() {
        super.end();

        OffensiveObject attacker = (OffensiveObject)getSubject();
        PerishableObject target = (PerishableObject)getTarget();

        attacker.setAnimation(UnitAnimation.Idle);
        events.attackComplete(attacker, target);
    }

    @Override
    public void failed() {
        super.failed();

        OffensiveObject attacker = (OffensiveObject)getSubject();
        PerishableObject target = (PerishableObject)getTarget();

        attacker.setAnimation(UnitAnimation.Idle);
        events.attackFailed(attacker, target);
    }

    private boolean attackValid(OffensiveObject attacker, PerishableObject target) {
        return attacker.isAlive() && target.isAlive() && inRange(attacker, target);
    }

    private boolean attackReady(OffensiveObject attacker, float time) {
        float newTime = Math.max(attacker.getAttackTime() - time, 0);
        attacker.setAttackTime(newTime);
        return newTime == 0;
    }

    private boolean attackTarget(OffensiveObject attacker, PerishableObject target) {
        attacker.setAttackTime(attacker.getAttackSpeed());
        attacker.setSound(UnitSound.Attack);
        reorient(attacker, target, false);
        damage.apply(attacker, target);
        return target.isDead();
    }
}

