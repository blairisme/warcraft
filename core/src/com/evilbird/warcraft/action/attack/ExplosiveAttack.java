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
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;

import javax.inject.Inject;

/**
 * An {@link Action} that causes a given {@link OffensiveObject combatant} to
 * attack a {@link PerishableObject} using explosives, killing
 *
 * @author Blair Butterworth
 */
public class ExplosiveAttack extends InstantAttack
{
    private AttackDamage damage;

    @Inject
    public ExplosiveAttack(AttackDamage damage, AttackEvents events, WarcraftPreferences preferences) {
        super(damage, events, preferences);
        this.damage = damage;
    }

    @Override
    protected void attackTarget() {
        super.attackTarget();
        damage.applyFull((PerishableObject)getSubject());
    }
}
