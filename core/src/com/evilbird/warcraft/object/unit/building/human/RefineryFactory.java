/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.building.BuildingFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.data.upgrade.Upgrade.OilProduction1;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.Refinery;

/**
 * Instances of this class create Human Refinery, a building that provides
 * access to advanced ships.
 *
 * @author Blair Butterworth
 */
public class RefineryFactory extends BuildingFactoryBase
{
    @Inject
    public RefineryFactory(Device device) {
        this(device.getAssetStorage());
    }

    public RefineryFactory(AssetManager manager) {
        super(manager, Refinery);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(600);
        result.setHealthMaximum(600);
        result.setIdentifier(objectIdentifier("Refinery", result));
        result.setSight(tiles(3));
        result.setType(Refinery);
        result.setUpgrade(OilProduction1);
        return result;
    }
}
