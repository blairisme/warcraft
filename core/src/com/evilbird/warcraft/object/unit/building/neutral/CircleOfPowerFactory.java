/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.building.BuildingFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.CircleOfPower;

/**
 * Instances of this class create {@link Building Circles of Power}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class CircleOfPowerFactory extends BuildingFactoryBase
{
    @Inject
    public CircleOfPowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public CircleOfPowerFactory(AssetManager manager) {
        super(manager, CircleOfPower);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setHealth(10000);
        result.setHealthMaximum(10000);
        result.setIdentifier(objectIdentifier("CircleOfPower", result));
        result.setSight(tiles(1));
        result.setType(CircleOfPower);
        result.setSelectable(false);
        return result;
    }
}
