/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.evilbird.warcraft.common.WarcraftSeason.Summer;
import static com.evilbird.warcraft.common.WarcraftSeason.Swamp;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;

/**
 * Common reusable test case for validate logic in building factories.
 *
 * @author Blair Butterworth
 */
public abstract class CombatantFactoryTestCase<T extends GameFactory> extends GameFactoryTestCase<T>
{
    protected abstract UnitType getBuildType();

    @Override
    protected Collection<GameContext> getLoadContexts() {
        UnitType type = getBuildType();
        WarcraftFaction faction = type.getFaction();
        return Arrays.asList(
                new WarcraftContext(faction, Summer),
                new WarcraftContext(faction, Swamp),
                new WarcraftContext(faction, Winter));
    }

    @Override
    protected Collection<Identifier> getProductIdentifiers() {
        UnitType type = getBuildType();
        return Collections.singleton(type);
    }
}