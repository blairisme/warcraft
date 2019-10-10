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
import static com.evilbird.warcraft.item.unit.UnitType.GuardTower;

/**
 * Instances of this unit test validate logic in the {@link GuardTowerFactory} class.
 *
 * @author Blair Butterworth
 */
public class GuardTowerFactoryTest extends BuildingFactoryTestCase<GuardTowerFactory>
{
    @Override
    protected UnitType getBuildType() {
        return GuardTower;
    }

    @Override
    protected GuardTowerFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new GuardTowerFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of(
                "animation", Idle,
                "basicDamage", 8,
                "piercingDamage", 4,
                "armour", 20,
                "health", 130.0f,
                "healthMaximum", 130.0f,
                "selectable", true,
                "selected", false,
                "sight", tiles(9),
                "attackRange", tiles(6),
                "type", GuardTower);
    }
}