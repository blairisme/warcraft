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
import static com.evilbird.warcraft.item.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Arrow;
import static com.evilbird.warcraft.item.unit.UnitType.GuardTower;

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
