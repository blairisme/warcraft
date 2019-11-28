/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.projectile.projectiles;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.projectile.ExplosiveProjectile;
import com.evilbird.warcraft.object.projectile.Projectile;
import com.evilbird.warcraft.object.projectile.ProjectileFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.effect.EffectType.Explosion;
import static com.evilbird.warcraft.object.projectile.ProjectileType.DaemonFire;

/**
 * A factory for the creation of daemon fire projectiles, loading the
 * necessary assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class DaemonFireFactory extends ProjectileFactoryBase
{
    @Inject
    public DaemonFireFactory(Device device) {
        super(device.getAssetStorage(), DaemonFire);
    }

    @Override
    public Projectile get(Identifier type) {
        ExplosiveProjectile projectile = builder.buildExplosive();
        projectile.setType(DaemonFire);
        projectile.setIdentifier(objectIdentifier("DaemonFire", projectile));
        projectile.setSize(32, 32);
        projectile.setExplosiveEffect(Explosion);
        return projectile;
    }
}
