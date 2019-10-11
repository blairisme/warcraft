/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.building.BuildingFactoryBase;
import com.evilbird.warcraft.item.unit.building.Tower;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannonball;
import static com.evilbird.warcraft.item.unit.UnitType.CannonTower;

/**
 * Instances of this class create {@link Building Guard Towers}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class CannonTowerFactory extends BuildingFactoryBase
{
    @Inject
    public CannonTowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public CannonTowerFactory(AssetManager manager) {
        super(manager, CannonTower);
    }

    @Override
    public Building get(Identifier type) {
        Tower result = builder.buildOffensiveBuilding();
        result.setAttackSpeed(3);
        result.setAttackRange(tiles(7));
        result.setArmour(20);
        result.setBasicDamage(50);
        result.setPiercingDamage(0);
        result.setHealth(160);
        result.setHealthMaximum(160);
        result.setIdentifier(objectIdentifier("CannonTower", result));
        result.setSight(tiles(9));
        result.setType(CannonTower);
        result.setProjectileType(Cannonball);
        return result;
    }
}
