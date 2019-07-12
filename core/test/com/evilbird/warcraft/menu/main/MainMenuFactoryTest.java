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
import com.evilbird.engine.device.Device;
import com.evilbird.test.testcase.GameTestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.test.data.device.TestDevices.newTestDevice;

/**
 * Instances of this unit test validate logic in the {@link MainMenuFactory}
 * class.
 *
 * @author Blair Butterworth
 */
public class MainMenuFactoryTest extends GameTestCase
{
    private static final String BUTTON = "data/textures/common/menu/button.png";
    private static final String BACKGROUND = "data/textures/common/menu/menu.png";
    private static final String CLICK = "data/sounds/common/menu/click.mp3";
    private static final String MUSIC = "data/music/13.mp3";

    private Device device;
    private AssetManager assets;
    private MainMenuFactory factory;

    @Before
    public void setup() {
        device = newTestDevice();
        assets = device.getAssetStorage();
        factory = new MainMenuFactory(device);
    }

    @Test
    public void loadTest() {
        factory.load(null);
        Mockito.verify(assets).load(BUTTON, Texture.class);
        Mockito.verify(assets).load(BACKGROUND, Texture.class);
        Mockito.verify(assets).load(MUSIC, Music.class);
    }

    @Test
    public void getTest() {
        factory.load(null);
        for (MainMenuType menuType: MainMenuType.values()) {
            MainMenu menu = factory.get(menuType);
            Assert.assertNotNull(menu);
            Assert.assertTrue(menu.getSkin().has("default", MainMenuStyle.class));
            Assert.assertTrue(menu.getSkin().has("default", TextButtonStyle.class));
        }
    }
}