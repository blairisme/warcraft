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
import com.evilbird.warcraft.object.projectile.Projectile;
import com.evilbird.warcraft.object.projectile.ProjectileFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.effect.EffectType.CannonExplosion;
import static com.evilbird.warcraft.object.projectile.ProjectileType.FlamingCannonball;

/**
 * A factory for the creation of flaming cannonball projectiles, loading the
 * necessary assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class FlamingCannonballFactory extends ProjectileFactoryBase
{
    @Inject
    public FlamingCannonballFactory(Device device) {
        super(device.getAssetStorage(), FlamingCannonball);
    }

    @Override
    public Projectile get(Identifier type) {
        Projectile projectile = builder.build();
        projectile.setType(FlamingCannonball);
        projectile.setIdentifier(objectIdentifier("FlamingCannonball", projectile));
        projectile.setSize(16, 16);
        projectile.setExplosionEffect(CannonExplosion);
        return projectile;
    }
}
