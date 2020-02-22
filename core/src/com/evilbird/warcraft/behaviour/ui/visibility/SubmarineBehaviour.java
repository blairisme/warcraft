/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.visibility;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourElement;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.attack.AttackEvent;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.death.RemoveEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.combatant.naval.Submarine;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.evilbird.warcraft.object.common.query.UnitOperations.isFlying;
import static com.evilbird.warcraft.object.common.query.UnitOperations.isSubmarine;

/**
 * A {@link Behaviour} implementation that exposes submarines to enemy players when
 * they are within sight of a flying unit.
 *
 * @author Blair Butterworth
 */
public class SubmarineBehaviour implements BehaviourElement
{
    private Events events;
    private GameObjectGraph graph;
    private Collection<GameObject> subsAttacking;
    private Map<GameObjectNode, Collection<GameObject>> subLocations;
    private Map<GameObjectNode, Collection<GameObject>> flyerLocations;

    @Inject
    public SubmarineBehaviour(Events events) {
        this.events = events;
    }

    @Override
    public void apply(State state, List<UserInput> input, float time) {
        if (!initialized()) {
            initialize(state.getWorld());
        }
        update();
    }

    private boolean initialized() {
        return subLocations != null;
    }

    private void initialize(GameObjectContainer state) {
        initializeGraph(state);
        initializeSubmarines(state);
        initializeFlyers(state);
    }

    private void initializeGraph(GameObjectContainer state) {
        graph = state.getSpatialGraph();
    }

    private void initializeSubmarines(GameObjectContainer state) {
        subLocations = new HashMap<>(5);
        subsAttacking = new ArrayList<>();

        for (GameObject submarine: state.findAll(UnitOperations::isSubmarine)) {
            addSubmarineLocations(submarine);
            setSubmarineVisible(submarine, false);

            if (GameObjectOperations.hasAction(submarine, AttackActions.values())) {
                subsAttacking.add(submarine);
            }
        }
    }

    private void initializeFlyers(GameObjectContainer state) {
        flyerLocations = new HashMap<>(5);
        
        for (GameObject flyer: state.findAll(UnitOperations::isFlying)) {
            Collection<GameObjectNode> locations = addFlyerLocation((Unit)flyer);
            evaluateSubmarineVisibility(locations);
        }
    }

    private void update() {
        evaluateCreateEvents();
        evaluateRemoveEvents();
        evaluateMoveEvents();
        evaluateAttackEvents();
    }

    private void evaluateCreateEvents() {
        for (CreateEvent createEvent: events.getEvents(CreateEvent.class)) {
            GameObject subject = createEvent.getSubject();
            if (isSubmarine(subject)) {
                Collection<GameObjectNode> locations = addSubmarineLocations(subject);
                evaluateSubmarineVisibility(locations);
            }
            else if (isFlying(subject)) {
                Collection<GameObjectNode> locations = addFlyerLocation((Unit)subject);
                evaluateSubmarineVisibility(locations);
            }
        }
    }

    private void evaluateRemoveEvents() {
        for (RemoveEvent removeEvent: events.getEvents(RemoveEvent.class)) {
            GameObject subject = removeEvent.getSubject();
            if (isSubmarine(subject)) {
                removeSubmarineLocations(subject);
            }
            else if (isFlying(subject)) {
                Collection<GameObjectNode> locations = removeFlyerLocation((Unit)subject);
                evaluateSubmarineVisibility(locations);
            }
        }
    }

    private void evaluateMoveEvents() {
        for (MoveEvent moveEvent: events.getEvents(MoveEvent.class)) {
            GameObject subject = moveEvent.getSubject();
            if (isSubmarine(subject)) {
                Collection<GameObjectNode> locations = updateSubmarineLocations(subject);
                evaluateSubmarineVisibility(locations);
            }
            else if (isFlying(subject)) {
                Collection<GameObjectNode> locations = updateFlyerLocations((Unit)subject);
                evaluateSubmarineVisibility(locations);
            }
        }
    }

    private void evaluateAttackEvents() {
        for (AttackEvent attackEvent: events.getEvents(AttackEvent.class)) {
            GameObject subject = attackEvent.getSubject();
            if (isSubmarine(subject)) {
                if (attackEvent.isStarting()) {
                    subsAttacking.add(subject);
                    setSubmarineVisible(subject, true);
                }
                else if (attackEvent.isFinished()) {
                    subsAttacking.remove(subject);
                    evaluateSubmarineVisibility(subject);
                }
            }
        }
    }

    private Collection<GameObjectNode> addSubmarineLocations(GameObject submarine) {
        Collection<GameObjectNode> locations = graph.getNodes(submarine);
        return addCache(subLocations, locations, submarine);
    }

    private Collection<GameObjectNode> removeSubmarineLocations(GameObject submarine) {
        return removeCache(subLocations, submarine);
    }

    private Collection<GameObjectNode> updateSubmarineLocations(GameObject submarine) {
        Collection<GameObjectNode> invalidated = new ArrayList<>();
        invalidated.addAll(removeSubmarineLocations(submarine));
        invalidated.addAll(addSubmarineLocations(submarine));
        return invalidated;
    }

    private Collection<GameObjectNode> addFlyerLocation(Unit flyer) {
        Collection<GameObjectNode> locations = graph.getNodes(flyer.getPosition(), flyer.getSize(), flyer.getSight());
        return addCache(flyerLocations, locations, flyer);
    }

    private Collection<GameObjectNode> removeFlyerLocation(Unit flyer) {
        return removeCache(flyerLocations, flyer);
    }

    private Collection<GameObjectNode> updateFlyerLocations(Unit flyer) {
        Collection<GameObjectNode> invalidated = new ArrayList<>();
        invalidated.addAll(removeFlyerLocation(flyer));
        invalidated.addAll(addFlyerLocation(flyer));
        return invalidated;
    }

    private Collection<GameObjectNode> addCache(
        Map<GameObjectNode, Collection<GameObject>> cache,
        Collection<GameObjectNode> nodes,
        GameObject entry)
    {
        for (GameObjectNode node: nodes) {
            Collection<GameObject> entries = Maps.getOrDefaultSupplied(cache, node, ArrayList::new);
            entries.add(entry);
            cache.put(node, entries);
        }
        return nodes;
    }

    private Collection<GameObjectNode> removeCache(
        Map<GameObjectNode, Collection<GameObject>> cache,
        GameObject element)
    {
        Collection<GameObjectNode> nodes = new ArrayList<>();
        for (Entry<GameObjectNode, Collection<GameObject>> entry: cache.entrySet()) {
            if (entry.getValue().remove(element)) {
                nodes.add(entry.getKey());
            }
        }
        return nodes;
    }

    private void evaluateSubmarineVisibility(GameObject submarine) {
        Collection<GameObjectNode> locations = graph.getNodes(submarine);
        evaluateSubmarineVisibility(locations);
    }

    private void evaluateSubmarineVisibility(Collection<GameObjectNode> locations) {
        for (GameObjectNode location: locations) {
            Collection<GameObject> subs = Maps.getOrDefault(subLocations, location, Collections.emptyList());
            Collection<GameObject> flyers = Maps.getOrDefault(flyerLocations, location, Collections.emptyList());

            if (!subs.isEmpty()) {
                setSubmarinesVisible(subs, !flyers.isEmpty());
            }
        }
    }

    private void setSubmarinesVisible(Collection<GameObject> submarines, boolean visible) {
        for (GameObject submarine: submarines) {
            setSubmarineVisible(submarine, visible);
        }
    }

    private void setSubmarineVisible(GameObject gameObject, boolean visible) {
        visible = subsAttacking.contains(gameObject) || visible;
        Submarine submarine = (Submarine) gameObject;
        submarine.setAttackable(visible);
        submarine.setVisible(UnitOperations.isCorporeal(submarine) || visible);
    }
}
