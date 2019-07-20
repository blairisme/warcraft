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

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.unit.UnitType.ScoutTower;

/**
 * Instances of this class create {@link Building Scout Towers}, entry level
 * attack buildings.
 *
 * @author Blair Butterworth
 */
public class ScoutTowerFactory extends BuildingFactoryBase
{
    @Inject
    public ScoutTowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ScoutTowerFactory(AssetManager manager) {
        super(manager, ScoutTower);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setDefence(20);
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setIdentifier(objectIdentifier("ScoutTower", result));
        result.setName("Scout Tower");
        result.setSight(tiles(9));
        result.setType(ScoutTower);
        return result;
    }
}