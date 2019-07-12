/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.device.DeviceControls;
import com.evilbird.test.data.device.TestAssets;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.item.ui.hud.HudControl;
import com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonStyle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link ControlPaneFactory} class.
 *
 * @author Blair Butterworth
 */
public class ControlPaneFactoryTest extends GameTestCase
{
    private static final String ACTION_BUTTON = "data/textures/common/menu/action.png";

    private ControlPaneFactory factory;
    private AssetManager assets;

    @Before
    public void setup() {
        assets = TestAssets.newAssetManagerMock();
        factory = new ControlPaneFactory(assets, Mockito.mock(DeviceControls.class));
    }

    @Test
    public void loadTest() {
        factory.load(null);
        Mockito.verify(assets).load(ACTION_BUTTON, Texture.class);
    }

    @Test
    public void getTest() {
        ControlPane controlPane = factory.get(null);
        Assert.assertNotNull(controlPane);
        Assert.assertEquals(HudControl.ControlPane, controlPane.getType());
        Assert.assertTrue(controlPane.getSkin().has("action-button", ActionButtonStyle.class));
    }
}