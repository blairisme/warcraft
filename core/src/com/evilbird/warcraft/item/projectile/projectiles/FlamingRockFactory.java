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
import static com.evilbird.warcraft.item.projectile.ProjectileType.FlamingRock;

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
        Projectile projectile = builder.build();
        projectile.setType(FlamingRock);
        projectile.setIdentifier(objectIdentifier("FlamingRock", projectile));
        projectile.setSize(32, 32);
        return projectile;
    }
}
