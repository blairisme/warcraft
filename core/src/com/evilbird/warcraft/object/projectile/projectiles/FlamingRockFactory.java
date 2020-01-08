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
import static com.evilbird.warcraft.object.effect.EffectType.SiegeExplosion;
import static com.evilbird.warcraft.object.projectile.ProjectileType.FlamingRock;

/**
 * A factory for the creation of flaming rock projectiles, loading the
 * necessary assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class FlamingRockFactory extends ProjectileFactoryBase
{
    @Inject
    public FlamingRockFactory(Device device) {
        super(device.getAssetStorage(), FlamingRock);
    }

    @Override
    public Projectile get(Identifier type) {
        ExplosiveProjectile projectile = builder.buildExplosive();
        projectile.setType(FlamingRock);
        projectile.setIdentifier(objectIdentifier("FlamingRock", projectile));
        projectile.setSize(32, 32);
        projectile.setExplosiveEffect(SiegeExplosion);
        return projectile;
    }
}
