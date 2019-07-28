/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceControls;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.item.ui.display.HudControl;
import com.evilbird.warcraft.item.ui.display.HudType;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonStyle;
import com.evilbird.warcraft.state.WarcraftContext;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.common.WarcraftSeason.Summer;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;

/**
 * Instances of this unit test validate the {@link ControlPaneFactory} class.
 *
 * @author Blair Butterworth
 */
public class ControlPaneFactoryTest extends GameFactoryTestCase<ControlPaneFactory> {

    @Override
    protected ControlPaneFactory newFactory(DeviceDisplay display, AssetManager assets) {
        DeviceControls controls = Mockito.mock(DeviceControls.class);
        return new ControlPaneFactory(assets, controls);
    }

    @Override
    protected Collection<GameContext> getLoadContexts() {
        return Arrays.asList(
            new WarcraftContext(Human, Summer),
            new WarcraftContext(Orc, Winter));
    }

    @Test
    public void getTest() {
        factory.load(new WarcraftContext(Human, Winter));
        assets.finishLoading();

        ControlPane controlPane = (ControlPane)factory.get(HudType.Default);
        Assert.assertNotNull(controlPane);
        Assert.assertEquals(HudControl.ControlPane, controlPane.getType());
        Assert.assertTrue(controlPane.getSkin().has("action-button", ActionButtonStyle.class));
    }

    @Override
    protected Collection<Identifier> getValueTypes() {
        return Collections.singleton(HudControl.ControlPane);
    }

    @Override
    protected Map<String, Object> getValueProperties() {
        return Collections.emptyMap();
    }
}