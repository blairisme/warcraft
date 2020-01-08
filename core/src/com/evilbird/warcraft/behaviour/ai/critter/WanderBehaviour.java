/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.critter;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.action.move.MoveEvents;
import com.evilbird.warcraft.action.move.MoveToVectorAction;
import com.evilbird.warcraft.behaviour.ai.AiBehaviourElement;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.unit.critter.Critter;
import org.apache.commons.lang3.time.StopWatch;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * A behaviour that randomly moves critters.
 *
 * @author Blair Butterworth
 */
public class WanderBehaviour implements AiBehaviourElement
{
    private static final int MOVE_MIN = 1;
    private static final int MOVE_MAX = 3;
    private static final int MOVE_DISTANCE = 3;
    private static final int MOVE_PERIOD = 1;

    private MoveEvents events;
    private Random random;
    private StopWatch timer;
    private List<GameObject> critters;
    private int distance;

    @Inject
    public WanderBehaviour(MoveEvents events) {
        this.events = events;
        this.random = new Random();
        this.timer = new StopWatch();
    }

    @Override
    public void applyBehaviour(GameObjectContainer state, float time) {
        initialize(state);
        update(state);
    }

    private void initialize(GameObjectContainer state) {
        if (critters == null) {
            timer.start();
            critters = new ArrayList<>(state.findAll(UnitOperations::isCritter));
            distance = state.getSpatialGraph().getNodeWidth() * MOVE_DISTANCE;
        }
    }

    private void update(GameObjectContainer state) {
        if (! critters.isEmpty() && timer.getTime(SECONDS) >= MOVE_PERIOD) {
            timer.reset();
            move(state);
            timer.start();
        }
    }

    private void move(GameObjectContainer state) {
        GameObjectGraph graph = state.getSpatialGraph();
        for (int i = 0; i < MOVE_MIN + random.nextInt(MOVE_MAX); i++) {
            GameObject critter = critters.get(random.nextInt(critters.size()));
            if (GameObjectOperations.isIdle(critter)) {
                move((Critter)critter, graph);
            }
        }
    }

    private void move(Critter critter, GameObjectGraph graph) {
        ItemPathFilter capability = new ItemPathFilter();
        capability.addTraversableCapability(critter.getMovementCapability());

        Collection<GameObjectNode> adjacent = graph.getAdjacentNodes(critter, distance);
        List<GameObjectNode> destinations = CollectionUtils.filter(adjacent, capability);

        if (! destinations.isEmpty()) {
            GameObjectNode destination = destinations.get(random.nextInt(destinations.size()));
            move(critter, destination.getWorldReference());
        }
    }

    private void move(Critter critter, Vector2 destination) {
        MoveToVectorAction moveAction = new MoveToVectorAction(events);
        moveAction.setSubject(critter);
        moveAction.setDestination(destination);
        critter.addAction(moveAction);
    }
}
