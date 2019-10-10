/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.building.BuildingFactoryBase;
import com.evilbird.warcraft.item.unit.building.OffensiveBuilding;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannonball;
import static com.evilbird.warcraft.item.unit.UnitType.BombardTower;

/**
 * Instances of this class create {@link Building Guard Towers}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class BombardTowerFactory extends BuildingFactoryBase
{
    @Inject
    public BombardTowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public BombardTowerFactory(AssetManager manager) {
        super(manager, BombardTower);
    }

    @Override
    public Building get(Identifier type) {
        OffensiveBuilding result = builder.buildOffensiveBuilding();
        result.setAttackSpeed(3);
        result.setAttackRange(tiles(7));
        result.setArmour(20);
        result.setBasicDamage(50);
        result.setPiercingDamage(0);
        result.setHealth(160);
        result.setHealthMaximum(160);
        result.setIdentifier(objectIdentifier("BombardTower", result));
        result.setSight(tiles(9));
        result.setType(BombardTower);
        result.setProjectileType(Cannonball);
        return result;
    }
}
