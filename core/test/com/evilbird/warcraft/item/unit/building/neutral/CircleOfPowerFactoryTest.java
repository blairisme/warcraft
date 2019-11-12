/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.BuildingFactoryTestCase;

import java.util.Map;

import static com.evilbird.warcraft.item.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitType.CircleOfPower;

/**
 * Instances of this unit test validate logic in the {@link CircleOfPowerFactory} class.
 *
 * @author Blair Butterworth
 */
public class CircleOfPowerFactoryTest extends BuildingFactoryTestCase<CircleOfPowerFactory>
{
    @Override
    protected UnitType getBuildType() {
        return CircleOfPower;
    }

    @Override
    protected CircleOfPowerFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new CircleOfPowerFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of(
                "Animation", Idle,
                "Armour", 0,
                "Health", 10000.0f,
                "HealthMaximum", 10000.0f,
                "selectable", false,
                "selected", false,
                "sight", tiles(1),
                "type", CircleOfPower);
    }
}