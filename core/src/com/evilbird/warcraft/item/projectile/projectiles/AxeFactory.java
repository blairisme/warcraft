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
import static com.evilbird.warcraft.item.projectile.ProjectileType.Axe;

/**
 * A factory for the creation of axe projectiles, loading the
 * necessary assets and generating new game objects.
 *
 * @author Blair Butterworth
 */
public class AxeFactory extends ProjectileFactoryBase
{
    @Inject
    public AxeFactory(Device device) {
        super(device.getAssetStorage(), Axe);
    }

    @Override
    public Projectile get(Identifier type) {
        Projectile projectile = builder.build();
        projectile.setType(Axe);
        projectile.setIdentifier(objectIdentifier("Axe", projectile));
        projectile.setSize(32, 32);
        return projectile;
    }
}
