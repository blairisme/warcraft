/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.main;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.state.WarcraftContext;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;

/**
 * Instances of this unit test validate logic in the {@link MainMenuFactory}
 * class.
 *
 * @author Blair Butterworth
 */
public class MainMenuFactoryTest extends GameFactoryTestCase<MainMenuFactory>
{
    @Override
    protected MainMenuFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new MainMenuFactory(display, assets);
    }

    @Override
    protected Collection<GameContext> getLoadContexts() {
        return Collections.singletonList(new WarcraftContext(Human, Winter));
    }

    @Override
    protected Collection<Identifier> getValueTypes() {
        return Arrays.asList(MainMenuType.values());
    }

    @Override
    protected Map<String, Object> getValueProperties() {
        return Collections.emptyMap();
    }

    @Test
    @Override
    public void unloadTest() {
    }
}