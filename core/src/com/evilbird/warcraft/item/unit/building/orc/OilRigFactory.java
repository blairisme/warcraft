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

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.unit.UnitType.OilRig;

/**
 * Instances of this class create Orcish Oil Rig, a building that allows
 * oil to be gathered by oil tankers.
 *
 * @author Blair Butterworth
 */
public class OilRigFactory extends BuildingFactoryBase
{
    @Inject
    public OilRigFactory(Device device) {
        this(device.getAssetStorage());
    }

    public OilRigFactory(AssetManager manager) {
        super(manager, OilRig);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setDefence(20);
        result.setHealth(650);
        result.setHealthMaximum(650);
        result.setIdentifier(objectIdentifier("OilRig", result));
        result.setName("Oil Rig");
        result.setSight(tiles(3));
        result.setType(OilRig);
        return result;
    }
}
