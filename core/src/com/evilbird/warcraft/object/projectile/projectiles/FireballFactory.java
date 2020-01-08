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
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.effect.EffectType.Explosion;
import static com.evilbird.warcraft.object.projectile.ExplosivePattern.LinearSequence;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Fireball;

/**
 * A factory for the creation of fireball projectiles, loading the
 * necessary assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class FireballFactory extends ProjectileFactoryBase
{
    @Inject
    public FireballFactory(Device device) {
        super(device.getAssetStorage(), Fireball);
    }

    @Override
    public Projectile get(Identifier type) {
        ExplosiveProjectile projectile = builder.buildExplosive();
        projectile.setType(Fireball);
        projectile.setIdentifier(objectIdentifier("Fireball", projectile));
        projectile.setSize(32, 32);
        projectile.setExplosiveRange(tiles(4));
        projectile.setExplosiveCount(3);
        projectile.setExplosiveEffect(Explosion);
        projectile.setExplosiveInterval(0.15f);
        projectile.setExplosivePattern(LinearSequence);
        return projectile;
    }
}
