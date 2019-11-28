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
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.effect.EffectType.Explosion;
import static com.evilbird.warcraft.object.projectile.ExplosivePattern.LinearSequence;
import static com.evilbird.warcraft.object.projectile.ProjectileType.GryphonHammer;

/**
 * A factory for the creation of gryphon hammer projectiles, loading the
 * necessary assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class GryphonHammerFactory extends ProjectileFactoryBase
{
    @Inject
    public GryphonHammerFactory(Device device) {
        super(device.getAssetStorage(), GryphonHammer);
    }

    @Override
    public Projectile get(Identifier type) {
        ExplosiveProjectile projectile = builder.buildExplosive();
        projectile.setType(GryphonHammer);
        projectile.setIdentifier(objectIdentifier("GryphonHammer", projectile));
        projectile.setSize(32, 32);
        projectile.setExplosiveRadius(tiles(4));
        projectile.setExplosiveCount(3);
        projectile.setExplosiveEffect(Explosion);
        projectile.setExplosiveInterval(0.15f);
        projectile.setExplosivePattern(LinearSequence);
        return projectile;
    }
}
