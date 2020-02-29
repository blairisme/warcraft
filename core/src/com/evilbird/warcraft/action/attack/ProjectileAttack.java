/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BranchAction;
import com.evilbird.warcraft.object.common.capability.RangedOffensiveObject;
import com.evilbird.warcraft.object.projectile.ProjectileType;

import javax.inject.Inject;
import java.util.List;

/**
 * Modifies the state of a {@link RangedOffensiveObject} to attack a given item
 * using a projectile fired from distance.
 *
 * @author Blair Butterworth
 */
public class ProjectileAttack extends BranchAction
{
    private BasicProjectileAttack basic;
    private MissileAttack missile;

    @Inject
    public ProjectileAttack(BasicProjectileAttack basic, MissileAttack missile) {
        super(basic, missile);
        this.basic = basic;
        this.missile = missile;
    }

    @Override
    protected Action getBranch(List<Action> actions) {
        RangedOffensiveObject ranged = (RangedOffensiveObject)getSubject();
        ProjectileType projectile = ranged.getProjectileType();
        return projectile.isExplosive() ? missile : basic;
    }
}
