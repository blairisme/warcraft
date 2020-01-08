/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.critter.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.common.WarcraftSeason.Swamp;
import static com.evilbird.warcraft.object.unit.UnitType.Sheep;

/**
 * Instances of this unit test validate logic in the {@link SheepFactory} class.
 *
 * @author Blair Butterworth
 */
public class SheepFactoryTest extends GameFactoryTestCase<SheepFactory>
{
    @Override
    protected SheepFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new SheepFactory(assets);
    }

    @Override
    protected Collection<GameContext> getLoadContexts() {
        return Arrays.asList(new WarcraftContext(Human, Swamp), new WarcraftContext(Orc, Swamp));
    }

    @Override
    protected Collection<Identifier> getProductIdentifiers() {
        return Collections.singleton(UnitType.Sheep);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of("type", Sheep);
    }
}