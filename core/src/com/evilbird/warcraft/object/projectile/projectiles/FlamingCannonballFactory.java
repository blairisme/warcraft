/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.projectile.projectiles;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.projectile.ExplosiveProjectile;
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
        ExplosiveProjectile projectile = builder.buildExplosive();
        projectile.setType(FlamingCannonball);
        projectile.setIdentifier(objectIdentifier("FlamingCannonball", projectile));
        projectile.setSize(16, 16);
        projectile.setExplosiveEffect(CannonExplosion);
        return projectile;
    }
}
