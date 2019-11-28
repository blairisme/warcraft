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
import static com.evilbird.warcraft.object.effect.EffectType.TouchOfDeathExplosion;
import static com.evilbird.warcraft.object.projectile.ProjectileType.TouchOfDeath;

/**
 * A factory for the creation of touch of death spell projectiles, loading the
 * necessary assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class TouchOfDeathFactory extends ProjectileFactoryBase
{
    @Inject
    public TouchOfDeathFactory(Device device) {
        super(device.getAssetStorage(), TouchOfDeath);
    }

    @Override
    public Projectile get(Identifier type) {
        ExplosiveProjectile projectile = builder.buildExplosive();
        projectile.setType(TouchOfDeath);
        projectile.setIdentifier(objectIdentifier("TouchOfDeath", projectile));
        projectile.setSize(32, 32);
        projectile.setExplosiveEffect(TouchOfDeathExplosion);
        return projectile;
    }
}
