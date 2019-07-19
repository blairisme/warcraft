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
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.common.WarcraftContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.test.data.device.TestDevices.newTestDevice;
import static com.evilbird.warcraft.common.WarcraftAssetSet.Winter;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;

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
    protected Collection<Identifier> getLoadContexts() {
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
}