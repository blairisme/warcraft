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
        return delegate.act(delta);
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
