/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai.submarine;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.engine.item.utility.ItemOperations;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.attack.AttackEvent;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.behaviour.ai.AiBehaviourElement;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.combatant.Submarine;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static com.evilbird.warcraft.item.common.query.UnitOperations.isFlying;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isSubmarine;

/**
 * An {@link AiBehaviourElement} that exposes submarines to enemy players when
 * they are within sight of a flying unit.
 *
 * @author Blair Butterworth
 */
public class SubmarineBehaviour implements AiBehaviourElement
{
    private Events events;
    private ItemGraph graph;
    private Collection<Item> subsAttacking;
    private Map<ItemNode, Collection<Item>> subLocations;
    private Map<ItemNode, Collection<Item>> flyerLocations;

    @Inject
    public SubmarineBehaviour(Events events) {
        this.events = events;
    }

    @Override
    public void applyBehaviour(ItemRoot state) {
        if (!initialized()) {
            initialize(state);
        }
        update();
    }

    private boolean initialized() {
        return subLocations != null;
    }

    private void initialize(ItemRoot state) {
        initializeGraph(state);
        initializeSubmarines(state);
        initializeFlyers(state);
    }

    private void initializeGraph(ItemRoot state) {
        graph = state.getSpatialGraph();
    }

    private void initializeSubmarines(ItemRoot state) {
        subLocations = new HashMap<>(5);
        subsAttacking = new ArrayList<>();

        for (Item submarine: state.findAll(UnitOperations::isSubmarine)) {
            addSubmarineLocations(submarine);
            setSubmarineVisible(submarine, false);

            if (ItemOperations.hasAction(submarine, AttackActions.values())) {
                subsAttacking.add(submarine);
            }
        }
    }

    private void initializeFlyers(ItemRoot state) {
        flyerLocations = new HashMap<>(5);
        
        for (Item flyer: state.findAll(UnitOperations::isFlying)) {
            Collection<ItemNode> locations = addFlyerLocation((Unit)flyer);
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
            Item subject = createEvent.getSubject();
            if (isSubmarine(subject)) {
                Collection<ItemNode> locations = addSubmarineLocations(subject);
                evaluateSubmarineVisibility(locations);
            }
            else if (isFlying(subject)) {
                Collection<ItemNode> locations = addFlyerLocation((Unit)subject);
                evaluateSubmarineVisibility(locations);
            }
        }
    }

    private void evaluateRemoveEvents() {
        for (RemoveEvent removeEvent: events.getEvents(RemoveEvent.class)) {
            Item subject = removeEvent.getSubject();
            if (isSubmarine(subject)) {
                removeSubmarineLocations(subject);
            }
            else if (isFlying(subject)) {
                Collection<ItemNode> locations = removeFlyerLocation((Unit)subject);
                evaluateSubmarineVisibility(locations);
            }
        }
    }

    private void evaluateMoveEvents() {
        for (MoveEvent moveEvent: events.getEvents(MoveEvent.class)) {
            Item subject = moveEvent.getSubject();
            if (isSubmarine(subject)) {
                Collection<ItemNode> locations = updateSubmarineLocations(subject);
                evaluateSubmarineVisibility(locations);
            }
            else if (isFlying(subject)) {
                Collection<ItemNode> locations = updateFlyerLocations((Unit)subject);
                evaluateSubmarineVisibility(locations);
            }
        }
    }

    private void evaluateAttackEvents() {
        for (AttackEvent attackEvent: events.getEvents(AttackEvent.class)) {
            Item subject = attackEvent.getSubject();
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

    private Collection<ItemNode> addSubmarineLocations(Item submarine) {
        Collection<ItemNode> locations = graph.getNodes(submarine);
        return addCache(subLocations, locations, submarine);
    }

    private Collection<ItemNode> removeSubmarineLocations(Item submarine) {
        return removeCache(subLocations, submarine);
    }

    private Collection<ItemNode> updateSubmarineLocations(Item submarine) {
        Collection<ItemNode> invalidated = new ArrayList<>();
        invalidated.addAll(removeSubmarineLocations(submarine));
        invalidated.addAll(addSubmarineLocations(submarine));
        return invalidated;
    }

    private Collection<ItemNode> addFlyerLocation(Unit flyer) {
        Collection<ItemNode> locations = graph.getNodes(flyer.getPosition(), flyer.getSize(), flyer.getSight());
        return addCache(flyerLocations, locations, flyer);
    }

    private Collection<ItemNode> removeFlyerLocation(Unit flyer) {
        return removeCache(flyerLocations, flyer);
    }

    private Collection<ItemNode> updateFlyerLocations(Unit flyer) {
        Collection<ItemNode> invalidated = new ArrayList<>();
        invalidated.addAll(removeFlyerLocation(flyer));
        invalidated.addAll(addFlyerLocation(flyer));
        return invalidated;
    }

    private Collection<ItemNode> addCache(Map<ItemNode, Collection<Item>> cache, Collection<ItemNode> nodes,Item entry){
        for (ItemNode node: nodes) {
            Collection<Item> entries = Maps.getOrDefault(cache, node, ArrayList::new);
            entries.add(entry);
            cache.put(node, entries);
        }
        return nodes;
    }

    private Collection<ItemNode> removeCache(Map<ItemNode, Collection<Item>> cache, Item element) {
        Collection<ItemNode> nodes = new ArrayList<>();
        for (Entry<ItemNode, Collection<Item>> entry: cache.entrySet()) {
            if (entry.getValue().remove(element)) {
                nodes.add(entry.getKey());
            }
        }
        return nodes;
    }

    private void evaluateSubmarineVisibility(Item submarine) {
        Collection<ItemNode> locations = graph.getNodes(submarine);
        evaluateSubmarineVisibility(locations);
    }

    private void evaluateSubmarineVisibility(Collection<ItemNode> locations) {
        for (ItemNode location: locations) {
            Collection<Item> subs = subLocations.getOrDefault(location, Collections.emptyList());
            Collection<Item> flyers = flyerLocations.getOrDefault(location, Collections.emptyList());

            if (!subs.isEmpty()) {
                setSubmarinesVisible(subs, !flyers.isEmpty());
            }
        }
    }

    private void setSubmarinesVisible(Collection<Item> submarines, boolean visible) {
        for (Item submarine: submarines) {
            setSubmarineVisible(submarine, visible);
        }
    }

    private void setSubmarineVisible(Item item, boolean visible) {
        visible = subsAttacking.contains(item) || visible;
        Submarine submarine = (Submarine)item;
        submarine.setAttackable(visible);
        submarine.setVisible(UnitOperations.isCorporeal(submarine) || visible);
    }
}
