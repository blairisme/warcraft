/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.outro;

import com.badlogic.gdx.assets.AssetManager;
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
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;

/**
 * Instances of this unit test validate logic in the {@link OutroMenuFactory}
 * class.
 *
 * @author Blair Butterworth
 */
public class OutroMenuFactoryTest extends GameFactoryTestCase<OutroMenuFactory>
{
    @Override
    protected OutroMenuFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new OutroMenuFactory(display, assets);
    }

    @Override
    protected Collection<GameContext> getLoadContexts() {
        return Arrays.asList(new WarcraftContext(Human, Winter), new WarcraftContext(Orc, Summer));
    }

    @Override
    protected Collection<Identifier> getProductIdentifiers() {
        return Arrays.asList(OutroMenuType.values());
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Collections.emptyMap();
    }
}