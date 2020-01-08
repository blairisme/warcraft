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
import static com.evilbird.warcraft.data.upgrade.Upgrade.GoldProduction2;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.Fortress;

/**
 * Instances of this class create Orcish Fortress, the central building of the
 * Orc faction and one that creates gathering units: Peons.
 *
 * @author Blair Butterworth
 */
public class FortressFactory extends BuildingFactoryBase
{
    @Inject
    public FortressFactory(Device device) {
        this(device.getAssetStorage());
    }

    public FortressFactory(AssetManager manager) {
        super(manager, Fortress);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(1600);
        result.setHealthMaximum(1600);
        result.setIdentifier(objectIdentifier("Fortress", result));
        result.setSight(tiles(9));
        result.setType(Fortress);
        result.setResource(Food, 1);
        result.setUpgrade(GoldProduction2);
        return result;
    }
}
