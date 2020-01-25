/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AiBehaviourTree implements Behaviour
{
    private Provider<PlayerBehaviour> behaviourFactory;
    private Collection<PlayerBehaviour> behaviours;

    @Inject
    public AiBehaviourTree(Provider<PlayerBehaviour> behaviourFactory) {
        this.behaviourFactory = behaviourFactory;
    }

    @Override
    public void update(State state, List<UserInput> input, float time) {
        if (behaviours == null) {
            Collection<Player> players = UnitOperations.getArtificialPlayers(state.getWorld());
            behaviours = new ArrayList<>(players.size());

            for (Player player: players) {
                PlayerBehaviour playerBehaviour = behaviourFactory.get();
                playerBehaviour.setPlayer(player);
                behaviours.add(playerBehaviour);
            }
        }
        for (PlayerBehaviour behaviour: behaviours) {
            behaviour.step();
        }
    }
}
