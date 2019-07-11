/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource.goldmine;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.test.testcase.FactoryTestCase;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.Resource;
import com.evilbird.warcraft.item.unit.resource.neutral.GoldMineFactory;

import java.util.Map;

/**
 * Instances of this unit test validate logic in the {@link GoldMineFactory} class.
 *
 * @author Blair Butterworth
 */
public class GoldMineFactoryTest extends FactoryTestCase<Resource>
{
    @Override
    protected GameFactory<Resource> newFactory(AssetManager assets) {
        return new GoldMineFactory(assets);
    }

    @Override
    protected Map<String, Object> newValueProperties() {
        return Maps.of("type", UnitType.GoldMine,
                "HealthMaximum", 2400.0f);
    }
}