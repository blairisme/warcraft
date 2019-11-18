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
import static com.evilbird.warcraft.object.projectile.ProjectileType.Cannonball;

/**
 * A factory for the creation of cannonball projectiles, loading the
 * necessary assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class CannonballFactory extends ProjectileFactoryBase
{
    @Inject
    public CannonballFactory(Device device) {
        super(device.getAssetStorage(), Cannonball);
    }

    @Override
    public Projectile get(Identifier type) {
        Projectile projectile = builder.build();
        projectile.setType(Cannonball);
        projectile.setIdentifier(objectIdentifier("Cannonball", projectile));
        projectile.setSize(32, 32);
        projectile.setExplosionEffect(CannonExplosion);
        return projectile;
    }
}
