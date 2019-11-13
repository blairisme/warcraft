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
import static com.evilbird.warcraft.object.projectile.ProjectileType.Bolt;

/**
 * A factory for the creation of bolt projectiles, loading the
 * necessary assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class BoltFactory extends ProjectileFactoryBase
{
    @Inject
    public BoltFactory(Device device) {
        super(device.getAssetStorage(), Bolt);
    }

    @Override
    public Projectile get(Identifier type) {
        Projectile projectile = builder.build();
        projectile.setType(Bolt);
        projectile.setIdentifier(objectIdentifier("Bolt", projectile));
        projectile.setSize(64, 64);
        return projectile;
    }
}
