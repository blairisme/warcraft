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
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.projectile.Projectile;
import com.evilbird.warcraft.object.projectile.ProjectileFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.effect.EffectType.LightningExplosion;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Lightning;

/**
 * A factory for the creation of lightning projectiles, loading the
 * necessary assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class LightningFactory extends ProjectileFactoryBase
{
    @Inject
    public LightningFactory(Device device) {
        super(device.getAssetStorage(), Lightning);
    }

    @Override
    public Projectile get(Identifier type) {
        Projectile projectile = builder.build();
        projectile.setType(Lightning);
        projectile.setIdentifier(objectIdentifier("Lightning", projectile));
        projectile.setSize(32, 32);
        projectile.setExplosionEffect(LightningExplosion);
        return projectile;
    }
}
