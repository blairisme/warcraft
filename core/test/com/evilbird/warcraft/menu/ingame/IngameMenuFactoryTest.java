/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.state.StateService;
import com.evilbird.test.testcase.GameTestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.test.data.device.TestAssets.newAssetManagerMock;

/**
 * Instances of this unit test validate logic in the {@link IngameMenuFactory}
 * class.
 *
 * @author Blair Butterworth
 */
public class IngameMenuFactoryTest extends GameTestCase
{
    private static final String BUTTON_ENABLED = "data/textures/human/menu/button-large-normal.png";

    private Device device;
    private AssetManager assets;
    private StateService states;
    private IngameMenuFactory factory;

    @Before
    public void setup() {
        assets = newAssetManagerMock();
        device = Mockito.mock(Device.class);
        Mockito.when(device.getAssetStorage()).thenReturn(assets);
        states = Mockito.mock(StateService.class);
        factory = new IngameMenuFactory(device, states);
    }

    @Test
    public void loadTest() {
        factory.load();
        Mockito.verify(assets).load(BUTTON_ENABLED, Texture.class);
    }

    @Test
    public void getTest() {
        factory.load();
        for (IngameMenuType menuType: IngameMenuType.values()) {
            IngameMenu menu = (IngameMenu)factory.get(menuType);
            Assert.assertNotNull(menu);
            Assert.assertTrue(menu.getSkin().has("menu-background-normal", Drawable.class));
        }
    }
}