/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.Timepiece;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Modifies the game state on behalf of the non-human players.
 *
 * @author Blair Butterworth
 */
public class AiBehaviourTree implements Behaviour
{
    private Provider<EnemyBehaviour> enemyBehaviourFactory;
    private Provider<NeutralBehaviour> neutralBehaviourFactory;
    private Collection<PlayerBehaviour> behaviours;
    private Timepiece timeService;

    @Inject
    public AiBehaviourTree(
        Provider<EnemyBehaviour> enemyBehaviourFactory,
        Provider<NeutralBehaviour> neutralBehaviourFactory)
    {
        this.enemyBehaviourFactory = enemyBehaviourFactory;
        this.neutralBehaviourFactory = neutralBehaviourFactory;
    }

    @Override
    public void update(State state, List<UserInput> input, float time) {
        initializeBehaviour(state);
        initializeTimeService();
        updateTimeService(time);
        updateBehaviours();
    }

    private void updateTimeService(float time) {
        timeService.update(time);
    }

    private void updateBehaviours() {
        for (PlayerBehaviour behaviour: behaviours) {
            behaviour.step();
        }
    }

    private void initializeTimeService() {
        if (timeService == null) {
            timeService = GdxAI.getTimepiece();
        }
    }

    private void initializeBehaviour(State state) {
        if (behaviours == null) {
            behaviours = new ArrayList<>(6);
            GameObjectContainer world = state.getWorld();
            initializeEnemyBehaviour(world);
            initializeNeutralBehaviour(world);
        }
    }

    private void initializeEnemyBehaviour(GameObjectContainer world) {
        for (Player player: UnitOperations.getArtificialPlayers(world)) {
            PlayerBehaviour playerBehaviour = enemyBehaviourFactory.get();
            playerBehaviour.setPlayer(player);
            behaviours.add(playerBehaviour);
        }
    }

    private void initializeNeutralBehaviour(GameObjectContainer world) {
        Player player = UnitOperations.getNeutralPlayer(world);
        if (player != null) {
            PlayerBehaviour playerBehaviour = neutralBehaviourFactory.get();
            playerBehaviour.setPlayer(player);
            behaviours.add(playerBehaviour);
        }
    }
}
