/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.Fonts;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.data.item.TestPlayers;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.hud.HudControl;
import com.evilbird.warcraft.item.hud.control.actions.ActionPane;
import com.evilbird.warcraft.item.hud.control.status.StatusPane;
import com.evilbird.warcraft.item.hud.resource.ResourcePane;
import com.evilbird.warcraft.item.hud.resource.ResourcePaneStyle;
import com.evilbird.warcraft.state.WarcraftState;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Instances of this unit test validate the {@link MenuBehaviour} class.
 *
 * @author Blair Butterworth
 */
public class MenuBehaviourTest extends GameTestCase
{
    private State state;
    private Player player;
    private ActionPane actionPane;
    private StatusPane statusPane;
    private ResourcePane resourcePane;
    private EventQueue events;
    private MenuBehaviour menuBehaviour;

    @Before
    public void setup() {
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = Fonts.ARIAL;

        ResourcePaneStyle resourceStyle = new ResourcePaneStyle();
        resourceStyle.font = Fonts.ARIAL;

        Skin skin = mock(Skin.class);
        when(skin.get(LabelStyle.class)).thenReturn(labelStyle);
        when(skin.get(anyString(), any())).then(invocation -> mock((Class<?>)invocation.getArguments()[1]));

        player = TestPlayers.newTestPlayer("player");
        resourcePane = new ResourcePane(resourceStyle);
        resourcePane.setIdentifier(HudControl.ResourcePane);
        actionPane = new ActionPane(skin);
        statusPane = new StatusPane(skin);

        ItemRoot world = TestItemRoots.newTestRoot("world");
        world.addItem(player);

        ItemRoot hud = TestItemRoots.newTestRoot("hud");
        hud.addItem(resourcePane);
        hud.addItem(actionPane);
        hud.addItem(statusPane);

        state = new WarcraftState(world, hud, null);

        events = new EventQueue();
        menuBehaviour = new MenuBehaviour(events);
    }

    @Test
    public void initializeTest() {
        menuBehaviour.update(state, Collections.emptyList());
    }

    @Test
    public void updateTest() {
        menuBehaviour.update(state, Collections.emptyList());
        menuBehaviour.update(state, Collections.emptyList());
    }
}