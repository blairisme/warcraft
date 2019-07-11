/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.test.testcase.FactoryTestCase;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import java.util.Map;

/**
 * Instances of this unit test validate logic in the {@link PeasantFactory} class.
 *
 * @author Blair Butterworth
 */
public class PeasantFactoryTest extends FactoryTestCase<Gatherer>
{
    @Override
    protected GameFactory<Gatherer> newFactory(AssetManager assets) {
        return new PeasantFactory(assets);
    }

    @Override
    protected Map<String, Object> newValueProperties() {
        return Maps.of("type", UnitType.Peasant,
                "HealthMaximum", 30.0f);
    }
}