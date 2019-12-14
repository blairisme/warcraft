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
import static com.evilbird.warcraft.data.resource.ResourceType.Food;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.Farm;

/**
 * Instances of this class create {@link Building Farms}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class FarmFactory extends BuildingFactoryBase
{
    @Inject
    public FarmFactory(Device device) {
        this(device.getAssetStorage());
    }

    public FarmFactory(AssetManager manager) {
        super(manager, Farm);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(400);
        result.setHealthMaximum(400);
        result.setIdentifier(objectIdentifier("Farm", result));
        result.setSight(tiles(3));
        result.setType(Farm);
        result.setResource(Food, 4);
        return result;
    }
}
