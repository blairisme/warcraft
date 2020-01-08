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
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.Metalworks;

/**
 * Instances of this class create Orcish Metalworks, a building that provides
 * upgrades for ships.
 *
 * @author Blair Butterworth
 */
public class MetalworksFactory extends BuildingFactoryBase
{
    @Inject
    public MetalworksFactory(Device device) {
        this(device.getAssetStorage());
    }

    public MetalworksFactory(AssetManager manager) {
        super(manager, Metalworks);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(750);
        result.setHealthMaximum(750);
        result.setIdentifier(objectIdentifier("Metalworks", result));
        result.setSight(tiles(3));
        result.setType(Metalworks);
        return result;
    }
}
