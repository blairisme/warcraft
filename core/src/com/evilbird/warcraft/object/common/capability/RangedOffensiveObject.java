/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.common.capability;

import com.evilbird.warcraft.object.projectile.Projectile;
import com.evilbird.warcraft.object.projectile.ProjectileType;

/**
 * Implementors of this interface represent an offensive object that can attack
 * an perishable object over distance.
 *
 * @author Blair Butterworth
 */
public interface RangedOffensiveObject extends OffensiveObject
{
    /**
     * Returns the objects current projectile, if any.
     */
    Projectile getProjectile();

    /**
     * Returns the type of projectile used when the ranged offensive unit
     * attacks.
     */
    ProjectileType getProjectileType();

    /**
     * Sets the projectile to be used by the ranged object.
     */
    void setProjectile(Projectile projectile);
}
