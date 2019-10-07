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
import com.evilbird.warcraft.item.unit.building.Fort;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.unit.UnitType.LookoutTower;

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
        Fort result = builder.buildFort();
        result.setAttackSpeed(1);
        result.setArmour(20);
        result.setDamageMinimum(4);
        result.setDamageMaximum(12);
        result.setHealth(130);
        result.setHealthMaximum(130);
        result.setIdentifier(objectIdentifier("LookoutTower", result));
        result.setSight(tiles(9));
        result.setRange(tiles(6));
        result.setType(LookoutTower);
        return result;
    }
}
