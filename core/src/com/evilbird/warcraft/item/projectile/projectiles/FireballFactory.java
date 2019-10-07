/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.projectile.projectiles;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.projectile.Projectile;
import com.evilbird.warcraft.item.projectile.ProjectileFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Fireball;

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
        Projectile projectile = builder.build();
        projectile.setType(Fireball);
        projectile.setIdentifier(objectIdentifier("Fireball", projectile));
        projectile.setSize(32, 32);
        return projectile;
    }
}
