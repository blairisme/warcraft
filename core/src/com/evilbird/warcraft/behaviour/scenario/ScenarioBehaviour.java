/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.scenario;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.action.menu.MenuActions;

import javax.inject.Inject;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

/**
 * Instances of this behaviour govern the success and failure conditions
 * applied to scenarios, primarily levels. When the conditions are met the user
 * is shown an appropriate menu, leading to the mission summary screen.
 *
 * @author Blair Butterworth
 */
public class ScenarioBehaviour implements Behaviour
{
    private EventQueue events;
    private ActionFactory actions;
    private BiPredicate<GameObjectContainer, EventQueue> winCondition;
    private BiPredicate<GameObjectContainer, EventQueue> loseCondition;
    private BiConsumer<GameObjectContainer, EventQueue> supplement;

    @Inject
    public ScenarioBehaviour(ActionFactory actions, EventQueue events) {
        this.events = events;
        this.actions = actions;
    }

    public void setWinCondition(BiPredicate<GameObjectContainer, EventQueue> winCondition) {
        this.winCondition = winCondition;
    }

    public void setLoseCondition(BiPredicate<GameObjectContainer, EventQueue> loseCondition) {
        this.loseCondition = loseCondition;
    }

    public void addBehaviour(BiConsumer<GameObjectContainer, EventQueue> behaviour) {
        supplement = supplement == null ? behaviour : supplement.andThen(behaviour);
    }

    @Override
    public void update(State state, List<UserInput> input, float time) {
        evaluate(state.getWorld());
    }

    private void evaluate(GameObjectContainer state) {
        if (winCondition.test(state, events)) {
            enterWinState(state);
        }
        if (loseCondition.test(state, events)) {
            enterLoseState(state);
        }
        if (supplement != null) {
            supplement.accept(state, events);
        }
    }

    private void enterWinState(GameObjectContainer state) {
        GameObject group = state.getBaseGroup();
        Action action = actions.get(MenuActions.VictoryMenu);
        action.setSubject(group);
        group.addAction(action);
    }

    private void enterLoseState(GameObjectContainer state) {
        GameObject group = state.getBaseGroup();
        Action action = actions.get(MenuActions.FailureMenu);
        action.setSubject(group);
        group.addAction(action);
    }
}
