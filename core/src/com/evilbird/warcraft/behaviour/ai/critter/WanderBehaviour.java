/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai.critter;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.engine.item.utility.ItemOperations;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.action.move.MoveToVectorAction;
import com.evilbird.warcraft.behaviour.ai.AiBehaviourElement;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.unit.critter.Critter;
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

    private Events events;
    private Random random;
    private StopWatch timer;
    private List<Item> critters;
    private int distance;

    @Inject
    public WanderBehaviour(Events events) {
        this.events = events;
        this.random = new Random();
        this.timer = new StopWatch();
    }

    @Override
    public void applyBehaviour(ItemRoot state) {
        initialize(state);
        update(state);
    }

    private void initialize(ItemRoot state) {
        if (critters == null) {
            timer.start();
            critters = new ArrayList<>(state.findAll(UnitOperations::isCritter));
            distance = state.getSpatialGraph().getNodeWidth() * MOVE_DISTANCE;
        }
    }

    private void update(ItemRoot state) {
        if (! critters.isEmpty() && timer.getTime(SECONDS) >= MOVE_PERIOD) {
            timer.reset();
            move(state);
            timer.start();
        }
    }

    private void move(ItemRoot state) {
        ItemGraph graph = state.getSpatialGraph();
        for (int i = 0; i < MOVE_MIN + random.nextInt(MOVE_MAX); i++) {
            Item critter = critters.get(random.nextInt(critters.size()));
            if (ItemOperations.isIdle(critter)) {
                move((Critter)critter, graph);
            }
        }
    }

    private void move(Critter critter, ItemGraph graph) {
        ItemPathFilter capability = new ItemPathFilter();
        capability.addTraversableCapability(critter.getMovementCapability());

        Collection<ItemNode> adjacent = graph.getAdjacentNodes(critter, distance);
        List<ItemNode> destinations = CollectionUtils.filter(adjacent, capability);

        if (! destinations.isEmpty()) {
            ItemNode destination = destinations.get(random.nextInt(destinations.size()));
            move(critter, destination.getWorldReference());
        }
    }

    private void move(Critter critter, Vector2 destination) {
        MoveToVectorAction moveAction = new MoveToVectorAction(events);
        moveAction.setItem(critter);
        moveAction.setDestination(destination);
        critter.addAction(moveAction);
    }
}
