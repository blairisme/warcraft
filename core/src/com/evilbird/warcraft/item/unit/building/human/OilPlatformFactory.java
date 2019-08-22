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
import static com.evilbird.warcraft.item.unit.UnitType.OilPlatform;

/**
 * Instances of this class create Human Oil Platform, a building that allows
 * oil to be gathered by oil tankers.
 *
 * @author Blair Butterworth
 */
public class OilPlatformFactory extends BuildingFactoryBase
{
    @Inject
    public OilPlatformFactory(Device device) {
        this(device.getAssetStorage());
    }

    public OilPlatformFactory(AssetManager manager) {
        super(manager, OilPlatform);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.newResourceExtractor();
        result.setArmour(20);
        result.setHealth(650);
        result.setHealthMaximum(650);
        result.setIdentifier(objectIdentifier("OilPlatform", result));
        result.setSight(tiles(3));
        result.setType(OilPlatform);
        return result;
    }
}
