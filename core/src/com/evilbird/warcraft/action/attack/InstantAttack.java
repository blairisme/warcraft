/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.common.state.OffensiveObject;
import com.evilbird.warcraft.item.common.state.PerishableObject;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.action.attack.AttackDamage.getDamagedHealth;

/**
 * A {@link ProximityAttack} that only attacks once.
 *
 * @author Blair Butterworth
 */
public class InstantAttack extends BasicAction
{
    private transient GameTimer delay;
    private transient WarcraftPreferences preferences;

    @Inject
    public InstantAttack(WarcraftPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean act(float time) {
        if (! targetAttacked()) {
            attackTarget();
        }
        if (! isComplete()) {
            return waitForComplete(time);
        }
        return ActionComplete;
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
        OffensiveObject attacker = (OffensiveObject)getItem();
        attacker.setAnimation(UnitAnimation.Attack);
        attacker.setSound(UnitSound.Attack, preferences.getEffectsVolume());

        PerishableObject target = (PerishableObject)getTarget();
        target.setHealth(getDamagedHealth(attacker, target));

        delay = new GameTimer(attacker.getAttackSpeed());
    }

    public boolean isComplete() {
        return delay != null && delay.complete();
    }

    protected boolean waitForComplete(float time) {
        delay.advance(time);
        return ActionIncomplete;
    }
}