/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceControls;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.object.GameObjectContainerType;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.object.display.components.UserInterfaceComponent;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonStyle;
import com.evilbird.warcraft.object.display.views.control.ControlPane;
import com.evilbird.warcraft.object.display.views.control.ControlPaneFactory;
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
        return new ControlPaneFactory(assets, Mockito.mock(DeviceControls.class));
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

        ControlPane controlPane = (ControlPane)factory.get(GameObjectContainerType.Hud);
        Assert.assertNotNull(controlPane);
        Assert.assertEquals(UserInterfaceComponent.ControlPane, controlPane.getType());
        Assert.assertTrue(controlPane.getSkin().has("action-button", ActionButtonStyle.class));
    }

    @Override
    protected Collection<Identifier> getProductIdentifiers() {
        return Collections.singleton(UserInterfaceComponent.ControlPane);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Collections.emptyMap();
    }
}