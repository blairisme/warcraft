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
import static com.evilbird.warcraft.object.unit.UnitType.OilRig;

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
        Building result = builder.buildExtractor();
        result.setArmour(20);
        result.setHealth(650);
        result.setHealthMaximum(650);
        result.setIdentifier(objectIdentifier("OilRig", result));
        result.setSight(tiles(3));
        result.setType(OilRig);
        return result;
    }
}
