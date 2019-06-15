/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.outro;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.device.Device;
import com.evilbird.test.testcase.GameTestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.test.data.device.TestDevices.newTestDevice;

/**
 * Instances of this unit test validate logic in the {@link OutroMenuFactory}
 * class.
 *
 * @author Blair Butterworth
 */
public class OutroMenuFactoryTest extends GameTestCase
{
    private static final String BUTTON = "data/textures/menu/button.png";
    private static final String INTRO_BUNDLE_1 = "data/strings/common/menu/outro";

    private Device device;
    private AssetManager assets;
    private OutroMenuFactory factory;

    @Before
    public void setup() {
        device = newTestDevice();
        assets = device.getAssetStorage();
        factory = new OutroMenuFactory(device);
    }

    @Test
    public void loadTest() {
        factory.load();
        Mockito.verify(assets).load(BUTTON, Texture.class);
        Mockito.verify(assets).load(INTRO_BUNDLE_1, I18NBundle.class);
    }

    @Test
    public void getTest() {
        factory.load();
        for (OutroMenuType menuType: OutroMenuType.values()) {
            OutroMenu menu = (OutroMenu)factory.get(menuType);
            Assert.assertNotNull(menu);
            Assert.assertTrue(menu.getSkin().has("background-victory", Drawable.class));
        }
    }
}