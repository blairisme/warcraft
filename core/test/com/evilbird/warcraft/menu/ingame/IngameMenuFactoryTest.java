/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.state.StateService;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.state.WarcraftContext;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;

/**
 * Instances of this unit test validate logic in the {@link IngameMenuFactory}
 * class.
 *
 * @author Blair Butterworth
 */
public class IngameMenuFactoryTest extends GameFactoryTestCase<IngameMenuFactory>
{
    @Override
    protected IngameMenuFactory newFactory(DeviceDisplay display, AssetManager assets) {
        StateService stateService = Mockito.mock(StateService.class);
        WarcraftPreferences preferences = Mockito.mock(WarcraftPreferences.class);
        return new IngameMenuFactory(display, stateService, assets, preferences);
    }

    @Override
    protected Collection<GameContext> getLoadContexts() {
        return Arrays.asList(new WarcraftContext(Human, Winter), new WarcraftContext(Orc, Winter));
    }

    @Override
    protected Collection<Identifier> getProductIdentifiers() {
        return Arrays.asList(IngameMenuType.values());
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Collections.emptyMap();
    }
}