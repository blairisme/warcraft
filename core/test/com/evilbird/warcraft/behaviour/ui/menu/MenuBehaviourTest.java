/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.common.text.Fonts;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.data.item.TestPlayers;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.action.common.transfer.TransferEvent;
import com.evilbird.warcraft.action.selection.SelectEvent;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionPane;
import com.evilbird.warcraft.object.display.components.common.IconSet;
import com.evilbird.warcraft.object.display.components.status.StatusPane;
import com.evilbird.warcraft.object.display.components.status.details.DetailsPaneStrings;
import com.evilbird.warcraft.object.display.components.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.object.display.views.resource.ResourceBar;
import com.evilbird.warcraft.object.display.views.resource.ResourceBarStyle;
import com.evilbird.warcraft.state.WarcraftState;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.evilbird.test.data.assets.TestStringBundles.newMockBundle;
import static com.evilbird.test.data.assets.TestTextures.newTestDrawable;
import static com.evilbird.test.data.assets.TestTextures.newTestTexture;
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
    private WarcraftState state;
    private Player player;
    private ActionPane actionPane;
    private StatusPane statusPane;
    private ResourceBar resourceBar;
    private EventQueue events;
    private MenuBehaviour menuBehaviour;

    @Before
    public void setup() {
        super.setup();

        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = Fonts.ARIAL;

        ResourceBarStyle resourceStyle = new ResourceBarStyle();
        resourceStyle.font = Fonts.ARIAL;

        DetailsPaneStyle detailsPaneStyle = new DetailsPaneStyle();
        detailsPaneStyle.background = newTestDrawable();
        detailsPaneStyle.productionBackground = newTestDrawable();
        detailsPaneStyle.strings = new DetailsPaneStrings(newMockBundle(), newMockBundle());
        detailsPaneStyle.icons = new IconSet(newTestTexture());

        Skin skin = mock(Skin.class);
        when(skin.get(LabelStyle.class)).thenReturn(labelStyle);
        when(skin.get(anyString(), any())).then(invocation -> mock((Class<?>)invocation.getArguments()[1]));
        when(skin.get("default", LabelStyle.class)).thenReturn(labelStyle);
        when(skin.get("mana-bar", LabelStyle.class)).thenReturn(labelStyle);
        when(skin.get(DetailsPaneStyle.class)).thenReturn(detailsPaneStyle);

        player = TestPlayers.newTestPlayer("player");
        resourceBar = new ResourceBar(resourceStyle);
        actionPane = new ActionPane(skin);
        statusPane = new StatusPane(skin);

        GameObjectContainer world = TestItemRoots.newTestRoot(new TextIdentifier("world"), player);
        GameObjectContainer hud = TestItemRoots.newTestRoot(new TextIdentifier("hud"), resourceBar, actionPane, statusPane);

        state = new WarcraftState();
        state.setWorld(world);
        state.setHud(hud);
        events = new EventQueue();
        menuBehaviour = new MenuBehaviour(events);
    }

    @Test
    public void initializeTest() {
        menuBehaviour.update(state, Collections.emptyList(), 1);
    }

    @Test
    public void updateTest() {
        menuBehaviour.update(state, Collections.emptyList(), 1);
        menuBehaviour.update(state, Collections.emptyList(), 1);
    }

    @Test
    public void resourceUpdateTest() {
        menuBehaviour.update(state, Collections.emptyList(), 1);
        resourceUpdateTest(ResourceType.Gold, 200.3f);
        resourceUpdateTest(ResourceType.Oil, 123.7f);
        resourceUpdateTest(ResourceType.Wood, 555.9f);
    }

    private void resourceUpdateTest(ResourceType resource, float value) {
        events.add(new TransferEvent(player, resource, value));
        menuBehaviour.update(state, Collections.emptyList(), 1);

        assertEquals(String.valueOf(Math.round(value)), resourceBar.getResourceText(resource));
        assertEquals(value, actionPane.getResource(resource), 0.1);
    }

    @Test
    public void selectionUpdateTest() {
        menuBehaviour.update(state, Collections.emptyList(), 1);

        GameObject gameObject1 = TestCombatants.newTestCombatant("item");
        GameObject gameObject2 = TestCombatants.newTestCombatant("item");
        events.add(new SelectEvent(gameObject1, true));
        events.add(new SelectEvent(gameObject2, true));
        menuBehaviour.update(state, Collections.emptyList(), 1);

        Collection<GameObject> expected1 = Arrays.asList(gameObject1, gameObject2);
        assertEquals(expected1, actionPane.getSelected());
        assertEquals(expected1, statusPane.getSelected());

        events.clear();
        events.add(new SelectEvent(gameObject1, false));
        menuBehaviour.update(state, Collections.emptyList(), 1);

        Collection<GameObject> expected2 = Arrays.asList(gameObject2);
        assertEquals(expected2, actionPane.getSelected());
        assertEquals(expected2, statusPane.getSelected());
    }
}