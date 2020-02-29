/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitSound;

import javax.inject.Inject;

/**
 * A {@link ProximityAttack} that only attacks once.
 *
 * @author Blair Butterworth
 */
public class InstantAttack extends BasicAction
{
    private transient AttackDamage damage;
    private transient AttackEvents events;
    private transient GameTimer delay;

    @Inject
    public InstantAttack(AttackDamage damage, AttackEvents events) {
        this.damage = damage;
        this.events = events;
    }

    @Override
    public ActionResult act(float time) {
        if (! targetAttacked()) {
            attackTarget();
        }
        if (! isComplete()) {
            return waitForComplete(time);
        }
        return ActionResult.Complete;
    }

    @Override
    public void reset() {
        super.reset();
        delay = null;
    }

    @Override
    public void restart() {
        super.restart();
        delay = null;
    }

    private boolean targetAttacked() {
        return delay != null;
    }

    protected void attackTarget() {
        OffensiveObject attacker = (OffensiveObject)getSubject();
        PerishableObject target = (PerishableObject)getTarget();

        setAttackerAnimation(attacker);
        damage.apply(attacker, target);
        events.attack(attacker, target);
        delay = new GameTimer(attacker.getAttackSpeed());
    }

    private void setAttackerAnimation(OffensiveObject attacker) {
        if (attacker.hasAnimation(UnitAnimation.Attack)) {
            attacker.setAnimation(UnitAnimation.Attack);
        }
        if (attacker.hasSound(UnitSound.Attack)) {
            attacker.setSound(UnitSound.Attack);
        }
    }

    private boolean isComplete() {
        return delay != null && delay.complete();
    }

    private ActionResult waitForComplete(float time) {
        delay.advance(time);
        return ActionResult.Incomplete;
    }
}