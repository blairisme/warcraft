/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.action.move.MoveWithinRangeAction;
import com.evilbird.warcraft.item.common.state.OffensiveObject;
import com.evilbird.warcraft.item.common.state.RangedOffensiveObject;
import com.evilbird.warcraft.item.projectile.Projectile;

import javax.inject.Inject;

/**
 * @author Blair Butterworth
 */
public class RangedAttack extends AttackSequence
{
    @Inject
    public RangedAttack(AttackEvents events, MoveWithinRangeAction move, ProjectileAttack attack, DeathAction death) {
        super(events, move, attack, death);
    }

    @Override
    protected void resetAttacker(OffensiveObject attacker) {
        RangedOffensiveObject rangedAttacker = (RangedOffensiveObject)attacker;
        Projectile projectile = rangedAttacker.getProjectile();
        if (projectile != null) {
            projectile.setVisible(false);
        }
    }
}
