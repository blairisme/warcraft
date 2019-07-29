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

import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitType.Stronghold;

/**
 * Instances of this unit test validate logic in the {@link StrongholdFactory} class.
 *
 * @author Blair Butterworth
 */
public class StrongholdFactoryTest extends BuildingFactoryTestCase<StrongholdFactory>
{
    @Override
    protected UnitType getBuildType() {
        return Stronghold;
    }

    @Override
    protected StrongholdFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new StrongholdFactory(assets);
    }

    @Override
    protected Map<String, Object> getValueProperties() {
        return Maps.of(
                "Animation", Idle,
                "Armour", 20,
                "Health", 1400.0f,
                "HealthMaximum", 1400.0f,
                "selectable", true,
                "selected", false,
                "sight", tiles(6),
                "type", Stronghold);
    }
}