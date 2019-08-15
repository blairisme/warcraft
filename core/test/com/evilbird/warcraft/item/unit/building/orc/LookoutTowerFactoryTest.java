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
import static com.evilbird.warcraft.item.unit.UnitType.LookoutTower;

/**
 * Instances of this unit test validate logic in the {@link LookoutTowerFactory} class.
 *
 * @author Blair Butterworth
 */
public class LookoutTowerFactoryTest extends BuildingFactoryTestCase<LookoutTowerFactory>
{
    @Override
    protected UnitType getBuildType() {
        return LookoutTower;
    }

    @Override
    protected LookoutTowerFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new LookoutTowerFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of(
                "Animation", Idle,
                "damageMinimum", 4,
                "damageMaximum", 12,
                "Armour", 20,
                "Health", 130.0f,
                "HealthMaximum", 130.0f,
                "selectable", true,
                "selected", false,
                "sight", tiles(9),
                "range", tiles(6),
                "type", LookoutTower);
    }
}