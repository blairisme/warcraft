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
import com.evilbird.engine.behaviour.BehaviourElement;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.behaviour.WarcraftBehaviour;
import com.evilbird.warcraft.behaviour.ai.operation.player.CorporealBehaviour;
import com.evilbird.warcraft.behaviour.ai.operation.player.EnemyBehaviour;
import com.evilbird.warcraft.behaviour.ai.operation.player.NeutralBehaviour;
import com.evilbird.warcraft.behaviour.ai.operation.player.PlayerBehaviour;
import com.evilbird.warcraft.behaviour.ai.order.OperationOrder;
import com.evilbird.warcraft.behaviour.ai.order.OperationOrderFactory;
import com.evilbird.warcraft.common.WarcraftFaction;
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
public class AiBehaviour implements BehaviourElement
{
    private Timepiece timeService;
    private WarcraftBehaviour behaviourType;
    private Collection<PlayerBehaviour> behaviours;
    private OperationOrderFactory operationOrderFactory;
    private Provider<EnemyBehaviour> enemyBehaviourFactory;
    private Provider<NeutralBehaviour> neutralBehaviourFactory;
    private Provider<CorporealBehaviour> corporealBehaviourFactory;

    @Inject
    public AiBehaviour(
        Provider<CorporealBehaviour> corporealBehaviourFactory,
        Provider<EnemyBehaviour> enemyBehaviourFactory,
        Provider<NeutralBehaviour> neutralBehaviourFactory,
        OperationOrderFactory operationOrderFactory)
    {
        this.corporealBehaviourFactory = corporealBehaviourFactory;
        this.enemyBehaviourFactory = enemyBehaviourFactory;
        this.neutralBehaviourFactory = neutralBehaviourFactory;
        this.operationOrderFactory = operationOrderFactory;
    }

    public void setBehaviourType(WarcraftBehaviour behaviourType) {
        this.behaviourType = behaviourType;
    }

    @Override
    public void apply(State state, List<UserInput> input, float time) {
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
            initializeCorporealBehaviour(world);
        }
    }

    private void initializeCorporealBehaviour(GameObjectContainer world) {
        Player player = UnitOperations.getCorporealPlayer(world);
        if (player != null) {
            PlayerBehaviour behaviour = corporealBehaviourFactory.get();
            OperationOrder order = operationOrderFactory.corporealBehaviour();
            addBehaviour(player, behaviour, order);
        }
    }

    private void initializeEnemyBehaviour(GameObjectContainer world) {
        for (Player player: UnitOperations.getArtificialPlayers(world)) {
            PlayerBehaviour behaviour = enemyBehaviourFactory.get();
            WarcraftFaction faction = player.getFaction();
            OperationOrder order = operationOrderFactory.enemyBehaviour(faction, behaviourType);
            addBehaviour(player, behaviour, order);
        }
    }

    private void initializeNeutralBehaviour(GameObjectContainer world) {
        Player player = UnitOperations.getNeutralPlayer(world);
        if (player != null) {
            PlayerBehaviour behaviour = neutralBehaviourFactory.get();
            OperationOrder order = operationOrderFactory.neutralBehaviour();
            addBehaviour(player, behaviour, order);
        }
    }

    private void addBehaviour(Player player, PlayerBehaviour behaviour, OperationOrder order) {
        behaviour.setPlayer(player);
        behaviour.setOrder(order);
        behaviours.add(behaviour);
    }
}
