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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.device.Device;
import com.evilbird.test.testcase.GameTestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.test.data.device.TestAssets.newAssetManagerMock;

/**
 * Instances of this unit test validate logic in the {@link IntroMenuFactory}
 * class.
 *
 * @author Blair Butterworth
 */
public class IntroMenuFactoryTest extends GameTestCase
{
    private static final String BUTTON = "data/textures/menu/button.png";
    private static final String INTRO_BUNDLE_1 = "data/strings/intro1";

    private Device device;
    private AssetManager assets;
    private IntroMenuFactory factory;

    @Before
    public void setup() {
        assets = newAssetManagerMock();
        device = Mockito.mock(Device.class);
        Mockito.when(device.getAssetStorage()).thenReturn(assets);
        factory = new IntroMenuFactory(device);
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
        IntroMenu menu = (IntroMenu)factory.get(IntroMenuType.HumanLevel1);
        Assert.assertNotNull(menu);
        Assert.assertTrue(menu.getSkin().has("intro-background", Drawable.class));
    }
}