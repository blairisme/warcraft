/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.CompositeAction;
import com.evilbird.warcraft.object.common.capability.RangedOffensiveObject;
import com.evilbird.warcraft.object.projectile.ProjectileType;

import javax.inject.Inject;

/**
 * Modifies the state of a {@link RangedOffensiveObject} to attack a given item
 * using a projectile fired from distance.
 *
 * @author Blair Butterworth
 */
public class ProjectileAttack extends CompositeAction
{
    private Action delegate;
    private Action basic;
    private Action missile;

    @Inject
    public ProjectileAttack(BasicProjectileAttack basic, MissileAttack missile) {
        super(basic, missile);
        this.basic = basic;
        this.missile = missile;
    }

    @Override
    public boolean act(float delta) {
        if (delegate == null) {
            delegate = isExplosive() ? missile : basic;
        }
        return delegate.run(delta);
    }

    @Override
    public void reset() {
        super.reset();
        delegate = null;
    }

    private boolean isExplosive() {
        RangedOffensiveObject ranged = (RangedOffensiveObject)getSubject();
        ProjectileType projectile = ranged.getProjectileType();
        return projectile.isExplosive();
    }
}
