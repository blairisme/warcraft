/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.building.BuildingFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.data.resource.ResourceType.Food;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.PigFarm;

/**
 * Instances of this class create Pig Farms Halls, an Orcish {@link Building} that
 * increase the Orcish population cap.
 *
 * @author Blair Butterworth
 */
public class PigFarmFactory extends BuildingFactoryBase
{
    @Inject
    public PigFarmFactory(Device device) {
        this(device.getAssetStorage());
    }

    public PigFarmFactory(AssetManager manager) {
        super(manager, PigFarm);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(400);
        result.setHealthMaximum(400);
        result.setIdentifier(objectIdentifier("PigFarm", result));
        result.setSight(tiles(3));
        result.setType(PigFarm);
        result.setResource(Food, 4);
        return result;
    }
}
