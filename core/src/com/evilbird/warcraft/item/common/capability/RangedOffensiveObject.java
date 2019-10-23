/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.capability;

import com.evilbird.warcraft.item.projectile.Projectile;
import com.evilbird.warcraft.item.projectile.ProjectileType;

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
