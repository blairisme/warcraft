/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai;

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
public class AiBehaviour implements Behaviour
{
    private Timepiece timeService;
    private Collection<com.evilbird.warcraft.behaviour.ai.PlayerBehaviour> behaviours;
    private Provider<com.evilbird.warcraft.behaviour.ai.EnemyBehaviour> enemyBehaviourFactory;
    private Provider<com.evilbird.warcraft.behaviour.ai.NeutralBehaviour> neutralBehaviourFactory;
    private Provider<com.evilbird.warcraft.behaviour.ai.CorporealBehaviour> corporealBehaviourFactory;

    @Inject
    public AiBehaviour(
        Provider<CorporealBehaviour> corporealBehaviourFactory,
        Provider<EnemyBehaviour> enemyBehaviourFactory,
        Provider<NeutralBehaviour> neutralBehaviourFactory)
    {
        this.corporealBehaviourFactory = corporealBehaviourFactory;
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
        for (com.evilbird.warcraft.behaviour.ai.PlayerBehaviour behaviour: behaviours) {
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
            initializeCorporealBehaviour(world);
        }
    }

    private void initializeCorporealBehaviour(GameObjectContainer world) {
        Player player = UnitOperations.getCorporealPlayer(world);
        if (player != null) {
            addBehaviour(player, corporealBehaviourFactory.get());
        }
    }

    private void initializeEnemyBehaviour(GameObjectContainer world) {
        for (Player player: UnitOperations.getArtificialPlayers(world)) {
            addBehaviour(player, enemyBehaviourFactory.get());
        }
    }

    private void initializeNeutralBehaviour(GameObjectContainer world) {
        Player player = UnitOperations.getNeutralPlayer(world);
        if (player != null) {
            addBehaviour(player, neutralBehaviourFactory.get());
        }
    }

    private void addBehaviour(Player player, PlayerBehaviour behaviour) {
        behaviour.setPlayer(player);
        behaviours.add(behaviour);
    }
}
