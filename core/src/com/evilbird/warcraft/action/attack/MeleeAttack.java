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
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.common.state.MovableObject;
import com.evilbird.warcraft.item.common.state.OffensiveObject;
import com.evilbird.warcraft.item.common.state.PerishableObject;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.action.attack.AttackDamage.getDamagedHealth;
import static com.evilbird.warcraft.item.common.query.UnitOperations.reorient;

/**
 * Instances of this {@link Action} reduce the health of the Actions target.
 * The Action will end when the targets health reaches zero.
 *
 * @author Blair Butterworth
 */
public class MeleeAttack extends BasicAction
{
    private transient GameTimer delay;
    private transient WarcraftPreferences preferences;

    @Inject
    public MeleeAttack(WarcraftPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            initialize();
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
        delay = null;
    }

    @Override
    public void restart() {
        super.restart();
        delay = null;
    }

    private boolean initialized() {
        return delay != null;
    }

    private void initialize() {
        OffensiveObject attacker = (OffensiveObject)getItem();
        attacker.setAnimation(UnitAnimation.Attack);
        attacker.setSound(UnitSound.Attack, preferences.getEffectsVolume());

        if (attacker instanceof MovableObject) {
            PerishableObject target = (PerishableObject)getTarget();
            reorient((MovableObject)attacker, target, false);
        }
        delay = new GameTimer(attacker.getAttackSpeed());
    }

    private boolean readyToAttack() {
        return delay.complete();
    }

    private boolean delayAttack(float time) {
        delay.advance(time);
        return ActionIncomplete;
    }

    private boolean attackTarget() {
        OffensiveObject attacker = (OffensiveObject)getItem();
        attacker.setSound(UnitSound.Attack, preferences.getEffectsVolume());

        PerishableObject target = (PerishableObject)getTarget();
        target.setHealth(getDamagedHealth(attacker, target));

        if (attacker instanceof MovableObject) {
            reorient((MovableObject)attacker, target, false);
        }
        delay.reset();
        return target.getHealth() == 0;
    }

    private boolean attackComplete() {
        OffensiveObject combatant = (OffensiveObject)getItem();
        combatant.setAnimation(UnitAnimation.Idle);
        return ActionComplete;
    }
}

