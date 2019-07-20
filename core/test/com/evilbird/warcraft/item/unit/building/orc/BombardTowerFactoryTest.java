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
import static com.evilbird.warcraft.item.unit.UnitType.BombardTower;

/**
 * Instances of this unit test validate logic in the {@link BombardTowerFactory} class.
 *
 * @author Blair Butterworth
 */
public class BombardTowerFactoryTest extends BuildingFactoryTestCase<BombardTowerFactory>
{
    @Override
    protected UnitType getBuildType() {
        return BombardTower;
    }

    @Override
    protected BombardTowerFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new BombardTowerFactory(assets);
    }

    @Override
    protected Map<String, Object> getValueProperties() {
        return Maps.of(
                "Animation", Idle,
                "damageMinimum", 50,
                "damageMaximum", 50,
                "Defence", 20,
                "Health", 160.0f,
                "HealthMaximum", 160.0f,
                "selectable", true,
                "selected", false,
                "sight", tiles(9),
                "range", tiles(7),
                "type", BombardTower);
    }
}