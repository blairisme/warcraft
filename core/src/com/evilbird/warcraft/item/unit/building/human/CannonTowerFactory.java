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
import com.evilbird.warcraft.item.unit.building.Fort;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
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
        Fort result = builder.buildFort();
        result.setAttackSpeed(1);
        result.setArmour(20);
        result.setDamageMinimum(50);
        result.setDamageMaximum(50);
        result.setHealth(160);
        result.setHealthMaximum(160);
        result.setIdentifier(objectIdentifier("CannonTower", result));
        result.setSight(tiles(9));
        result.setRange(tiles(7));
        result.setType(CannonTower);
        return result;
    }
}
