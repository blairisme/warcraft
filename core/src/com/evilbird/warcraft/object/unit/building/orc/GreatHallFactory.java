/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
import static com.evilbird.warcraft.object.unit.UnitType.GreatHall;

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