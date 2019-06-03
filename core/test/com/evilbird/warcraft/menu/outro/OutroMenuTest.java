/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.outro;

import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.evilbird.test.data.display.TestSkin;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.data.item.TestPlayers;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.RETURNS_MOCKS;

/**
 * Instances of this unit test validate logic in the {@link OutroMenu}
 * class.
 *
 * @author Blair Butterworth
 */
public class OutroMenuTest extends GameTestCase
{
    private OutroMenu menu;

    @Before
    public void setup() {
        OutroMenuStrings bundle = Mockito.mock(OutroMenuStrings.class, RETURNS_MOCKS);
        menu = new OutroMenu(Mockito.mock(DeviceDisplay.class), TestSkin.newTestSkin());
        menu.setLabelBundle(bundle);
    }

    @Test
    public void setControllerTest() {
        ItemRoot world = TestItemRoots.newTestRoot("world");

        Player humanPlayer = TestPlayers.newTestPlayer(new TextIdentifier("human"), world);
        humanPlayer.setType(PlayerType.Corporeal);
        world.addItem(humanPlayer);

        Player aiPlayer = TestPlayers.newTestPlayer(new TextIdentifier("ai"), world);
        aiPlayer.setType(PlayerType.Artificial);
        world.addItem(aiPlayer);

        State state = Mockito.mock(State.class);
        Mockito.when(state.getWorld()).thenReturn(world);

        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getState()).thenReturn(state);

        menu.setController(engine);
    }
}
