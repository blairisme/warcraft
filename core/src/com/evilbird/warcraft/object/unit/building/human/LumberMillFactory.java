/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.building.BuildingFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.data.upgrade.Upgrade.WoodProduction1;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.LumberMill;

/**
 * Instances of this class create {@link Building LumberMill's}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class LumberMillFactory extends BuildingFactoryBase
{
    @Inject
    public LumberMillFactory(Device device) {
        this(device.getAssetStorage());
    }

    public LumberMillFactory(AssetManager manager) {
        super(manager, LumberMill);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(600);
        result.setHealthMaximum(600);
        result.setIdentifier(objectIdentifier("LumberMill", result));
        result.setSight(tiles(3));
        result.setType(LumberMill);
        result.setUpgrade(WoodProduction1);
        return result;
    }
}