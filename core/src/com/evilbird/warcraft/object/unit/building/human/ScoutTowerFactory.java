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
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.ScoutTower;

/**
 * Instances of this class create {@link Building Scout Towers}, entry level
 * attack buildings.
 *
 * @author Blair Butterworth
 */
public class ScoutTowerFactory extends BuildingFactoryBase
{
    @Inject
    public ScoutTowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ScoutTowerFactory(AssetManager manager) {
        super(manager, ScoutTower);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setIdentifier(objectIdentifier("ScoutTower", result));
        result.setSight(tiles(9));
        result.setType(ScoutTower);
        return result;
    }
}