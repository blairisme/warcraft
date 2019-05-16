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
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.data.item.TestPlayers;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.action.common.resource.ResourceTransferEvent;
import com.evilbird.warcraft.action.select.SelectEvent;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.hud.control.actions.ActionPane;
import com.evilbird.warcraft.item.hud.control.status.StatusPane;
import com.evilbird.warcraft.item.hud.resource.ResourcePane;
import com.evilbird.warcraft.item.hud.resource.ResourcePaneStyle;
import com.evilbird.warcraft.state.WarcraftState;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
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
        actionPane = new ActionPane(skin);
        statusPane = new StatusPane(skin);

        ItemRoot world = TestItemRoots.newTestRoot(new TextIdentifier("world"), player);
        ItemRoot hud = TestItemRoots.newTestRoot(new TextIdentifier("hud"), resourcePane, actionPane, statusPane);

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

    @Test
    public void resourceUpdateTest() {
        menuBehaviour.update(state, Collections.emptyList());
        resourceUpdateTest(ResourceType.Gold, 200.3f);
        resourceUpdateTest(ResourceType.Oil, 123.7f);
        resourceUpdateTest(ResourceType.Wood, 555.9f);
    }

    private void resourceUpdateTest(ResourceType resource, float value) {
        events.add(new ResourceTransferEvent(player, resource, 100f, value));
        menuBehaviour.update(state, Collections.emptyList());

        assertEquals(String.valueOf(Math.round(value)), resourcePane.getResourceText(resource));
        assertEquals(value, actionPane.getResource(resource), 0.1);
    }

    @Test
    public void selectionUpdateTest() {
        menuBehaviour.update(state, Collections.emptyList());

        Item item1 = TestCombatants.newTestCombatant("item");
        Item item2 = TestCombatants.newTestCombatant("item");
        events.add(new SelectEvent(item1, true));
        events.add(new SelectEvent(item2, true));
        menuBehaviour.update(state, Collections.emptyList());

        Collection<Item> expected1 = Arrays.asList(item1, item2);
        assertEquals(expected1, actionPane.getSelected());
        assertEquals(expected1, statusPane.getSelected());

        events.clear();
        events.add(new SelectEvent(item1, false));
        menuBehaviour.update(state, Collections.emptyList());

        Collection<Item> expected2 = Arrays.asList(item2);
        assertEquals(expected2, actionPane.getSelected());
        assertEquals(expected2, statusPane.getSelected());
    }
}