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
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.BuildingFactoryTestCase;

import java.util.Map;

import static com.evilbird.warcraft.item.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitType.GreatHall;

/**
 * Instances of this unit test validate logic in the {@link GreatHallFactory} class.
 *
 * @author Blair Butterworth
 */
public class GreatHallFactoryTest extends BuildingFactoryTestCase<GreatHallFactory>
{
    @Override
    protected UnitType getBuildType() {
        return GreatHall;
    }

    @Override
    protected GreatHallFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new GreatHallFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of(
                "Animation", Idle,
                "Armour", 20,
                "Health", 1200.0f,
                "HealthMaximum", 1200.0f,
                "selectable", true,
                "selected", false,
                "sight", tiles(4),
                "type", GreatHall);
    }
}