/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.intro;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.device.Device;
import com.evilbird.test.testcase.GameTestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.evilbird.test.data.device.TestDevices.newTestDevice;

/**
 * Instances of this unit test validate logic in the {@link IntroMenuFactory}
 * class.
 *
 * @author Blair Butterworth
 */
public class IntroMenuFactoryTest extends GameTestCase
{
    private Device device;
    private AssetManager assets;
    private IntroMenuFactory factory;

    @Before
    public void setup() {
        device = newTestDevice();
        assets = device.getAssetStorage();
        factory = new IntroMenuFactory(device);
    }

    @Test
    public void getTest() {
        for (IntroMenuType menuType: IntroMenuType.values()) {
            IntroMenu menu = (IntroMenu)factory.get(menuType);

            Assert.assertNotNull(menu);
            Assert.assertTrue(menu.getSkin().has("intro-background", Drawable.class));
        }
    }
}