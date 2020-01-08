/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.building.BuildingFactoryBase;
import com.evilbird.warcraft.object.unit.building.Tower;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Arrow;
import static com.evilbird.warcraft.object.unit.UnitType.GuardTower;

/**
 * Instances of this class create {@link Building Guard Towers}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class GuardTowerFactory extends BuildingFactoryBase
{
    @Inject
    public GuardTowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GuardTowerFactory(AssetManager manager) {
        super(manager, GuardTower);
    }

    @Override
    public Building get(Identifier type) {
        Tower result = builder.buildOffensiveBuilding();
        result.setAttackSpeed(3);
        result.setAttackRange(tiles(6));
        result.setArmour(20);
        result.setBasicDamage(8);
        result.setPiercingDamage(4);
        result.setHealth(130);
        result.setHealthMaximum(130);
        result.setIdentifier(objectIdentifier("GuardTower", result));
        result.setSight(tiles(9));
        result.setType(GuardTower);
        result.setProjectileType(Arrow);
        return result;
    }
}
