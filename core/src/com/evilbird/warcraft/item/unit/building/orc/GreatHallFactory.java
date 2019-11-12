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
import static com.evilbird.warcraft.item.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Food;
import static com.evilbird.warcraft.item.unit.UnitType.GreatHall;

/**
 * Instances of this class create Great Halls, an Orcish {@link Building} that
 * creates peons.
 *
 * @author Blair Butterworth
 */
public class GreatHallFactory extends BuildingFactoryBase {
    @Inject
    public GreatHallFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GreatHallFactory(AssetManager manager) {
        super(manager, GreatHall);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(1200);
        result.setHealthMaximum(1200);
        result.setIdentifier(objectIdentifier("GreatHall", result));
        result.setSight(tiles(4));
        result.setType(GreatHall);
        result.setResource(Food, 1);
        return result;
    }
}