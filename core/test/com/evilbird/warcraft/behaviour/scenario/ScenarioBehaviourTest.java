/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.scenario;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.testcase.GameTestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.warcraft.action.menu.MenuActions.FailureMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.VictoryMenu;
import static java.util.Collections.emptyList;

/**
 * Instances of this unit test validate the {@link ScenarioBehaviour} class.
 *
 * @author Blair Butterworth
 */
public class ScenarioBehaviourTest extends GameTestCase
{
    private State state;
    private ItemRoot root;
    private Action winAction;
    private Action loseAction;
    private ActionFactory actions;
    private EventQueue events;
    private ScenarioBehaviour behaviour;

    @Before
    public void setup() {
        winAction = Mockito.mock(Action.class);
        loseAction = Mockito.mock(Action.class);

        actions = Mockito.mock(ActionFactory.class);
        Mockito.when(actions.get(VictoryMenu)).thenReturn(winAction);
        Mockito.when(actions.get(FailureMenu)).thenReturn(loseAction);

        events = Mockito.mock(EventQueue.class);
        behaviour = new ScenarioBehaviour(actions, events);

        state = Mockito.mock(State.class);
        root = TestItemRoots.newTestRoot("root");
        Mockito.when(state.getWorld()).thenReturn(root);
    }

    @Test
    public void winTest() {
        behaviour.setWinCondition((a, b) -> false);
        behaviour.setLoseCondition((a, b) -> false);

        behaviour.update(state, emptyList());
        Assert.assertFalse(root.getBaseGroup().getActions().contains(winAction));

        behaviour.setWinCondition((a, b) -> true);
        behaviour.setLoseCondition((a, b) -> false);

        behaviour.update(state, emptyList());
        Assert.assertTrue(root.getBaseGroup().getActions().contains(winAction));
    }

    @Test
    public void loseTest() {
        behaviour.setWinCondition((a, b) -> false);
        behaviour.setLoseCondition((a, b) -> false);

        behaviour.update(state, emptyList());
        Assert.assertFalse(root.getBaseGroup().getActions().contains(loseAction));

        behaviour.setWinCondition((a, b) -> false);
        behaviour.setLoseCondition((a, b) -> true);

        behaviour.update(state, emptyList());
        Assert.assertTrue(root.getBaseGroup().getActions().contains(loseAction));
    }
}