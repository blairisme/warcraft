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
import static com.evilbird.warcraft.item.unit.UnitType.CannonTower;

/**
 * Instances of this unit test validate logic in the {@link CannonTowerFactory} class.
 *
 * @author Blair Butterworth
 */
public class CannonTowerFactoryTest extends BuildingFactoryTestCase<CannonTowerFactory>
{
    @Override
    protected UnitType getBuildType() {
        return CannonTower;
    }

    @Override
    protected CannonTowerFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new CannonTowerFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of(
            "animation", Idle,
            "basicDamage", 50,
            "piercingDamage", 0,
            "armour", 20,
            "health", 160.0f,
            "healthMaximum", 160.0f,
            "selectable", true,
            "selected", false,
            "sight", tiles(9),
            "attackRange", tiles(7),
            "type", CannonTower);
    }
}