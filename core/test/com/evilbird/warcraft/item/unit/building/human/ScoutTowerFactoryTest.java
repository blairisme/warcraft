/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.BuildingFactoryTestCase;

import java.util.Map;

import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitType.ScoutTower;

/**
 * Instances of this unit test validate logic in the {@link ScoutTowerFactory} class.
 *
 * @author Blair Butterworth
 */
public class ScoutTowerFactoryTest extends BuildingFactoryTestCase<ScoutTowerFactory>
{
    @Override
    protected UnitType getBuildType() {
        return ScoutTower;
    }

    @Override
    protected ScoutTowerFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new ScoutTowerFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of(
                "animation", Idle,
                "armour", 20,
                "health", 100.0f,
                "healthMaximum", 100.0f,
                "selectable", true,
                "selected", false,
                "sight", tiles(9),
                "type", ScoutTower);
    }
}