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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.common.WarcraftSeason.Summer;
import static com.evilbird.warcraft.common.WarcraftSeason.Swamp;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;

/**
 * Instances of this unit test validate logic in the {@link PeasantFactory} class.
 *
 * @author Blair Butterworth
 */
public class PeasantFactoryTest extends GameFactoryTestCase<PeasantFactory>
{
    @Override
    protected PeasantFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new PeasantFactory(assets);
    }

    @Override
    protected Collection<GameContext> getLoadContexts() {
        return Arrays.asList(
            new WarcraftContext(Human, Summer),
            new WarcraftContext(Human, Swamp),
            new WarcraftContext(Human, Winter),
            new WarcraftContext(Orc, Summer),
            new WarcraftContext(Orc, Swamp),
            new WarcraftContext(Orc, Winter));
    }

    @Override
    protected Collection<Identifier> getValueTypes() {
        return Collections.singleton(Peasant);
    }

    @Override
    protected Map<String, Object> getValueProperties() {
        return Maps.of("type", Peasant, "HealthMaximum", 30.0f);
    }
}