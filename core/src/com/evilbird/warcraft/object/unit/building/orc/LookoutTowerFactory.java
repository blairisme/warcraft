/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building.orc;

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
import static com.evilbird.warcraft.object.unit.UnitType.LookoutTower;

/**
 * Instances of this class create {@link Building Lookout Towers}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class LookoutTowerFactory extends BuildingFactoryBase
{
    @Inject
    public LookoutTowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public LookoutTowerFactory(AssetManager manager) {
        super(manager, LookoutTower);
    }

    @Override
    public Building get(Identifier type) {
        Tower result = builder.buildOffensiveBuilding();
        result.setAttackSpeed(1);
        result.setAttackRange(tiles(6));
        result.setArmour(20);
        result.setBasicDamage(8);
        result.setPiercingDamage(4);
        result.setHealth(130);
        result.setHealthMaximum(130);
        result.setIdentifier(objectIdentifier("LookoutTower", result));
        result.setSight(tiles(9));
        result.setType(LookoutTower);
        result.setProjectileType(Arrow);
        return result;
    }
}
