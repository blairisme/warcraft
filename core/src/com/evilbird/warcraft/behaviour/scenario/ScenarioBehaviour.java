/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.scenario;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.action.menu.MenuActions;

import javax.inject.Inject;
import java.util.List;

public class ScenarioBehaviour implements Behaviour
{
    private EventQueue events;
    private ActionFactory actions;
    private ScenarioCondition winCondition;
    private ScenarioCondition loseCondition;

    @Inject
    public ScenarioBehaviour(ActionFactory actions, EventQueue events) {
        this.events = events;
        this.actions = actions;
    }

    public void setWinCondition(ScenarioCondition winCondition) {
        this.winCondition = winCondition;
    }

    public void setLoseCondition(ScenarioCondition loseCondition) {
        this.loseCondition = loseCondition;
    }

    @Override
    public void update(State state, List<UserInput> input) {
        evaluate(state.getWorld());
    }

    private void evaluate(ItemRoot state) {
        if (winCondition.applicable(events) && winCondition.evaluate(state)) {
            enterWinState(state);
        }
        if (loseCondition.applicable(events) && loseCondition.evaluate(state)) {
            enterLoseState(state);
        }
    }

    private void enterWinState(ItemRoot state) {
        Item group = state.getBaseGroup();
        Action action = actions.newAction(MenuActions.VictoryMenu);
        action.setItem(group);
        group.addAction(action);
    }

    private void enterLoseState(ItemRoot state) {
        Item group = state.getBaseGroup();
        Action action = actions.newAction(MenuActions.FailureMenu);
        action.setItem(group);
        group.addAction(action);
    }
}
