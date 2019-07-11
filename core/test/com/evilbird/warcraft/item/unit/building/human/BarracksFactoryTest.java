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
import com.evilbird.engine.game.GameFactory;
import com.evilbird.test.testcase.FactoryTestCase;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import java.util.Map;

/**
 * Instances of this unit test validate logic in the {@link BarracksFactory} class.
 *
 * @author Blair Butterworth
 */
public class BarracksFactoryTest extends FactoryTestCase<Building>
{
    @Override
    protected GameFactory<Building> newFactory(AssetManager assets) {
        return new BarracksFactory(assets);
    }

    @Override
    protected Map<String, Object> newValueProperties() {
        return Maps.of("type", UnitType.Barracks,
                "HealthMaximum", 800.0f);
    }
}