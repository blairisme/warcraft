/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.item.common.state.OffensiveObject;
import com.evilbird.warcraft.item.common.state.PerishableObject;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.evilbird.engine.common.collection.CollectionUtils.filter;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isAlive;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isAnotherTeam;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isNeutral;

/**
 * Instances of this class are used to obtain attackable targets within range
 * of a given attacker, and vice versa.
 *
 * @author Blair Butterworth
 */
public class AttackController
{
    private Events events;
    private AttackGraph graph;
    private Map<Identifier, List<OffensiveObject>> attackers;
    private Map<Identifier, List<PerishableObject>> targets;

    public AttackController(Events events, AttackGraph graph) {
        this.events = events;
        this.graph = graph;
        this.targets = new HashMap<>();
        this.attackers = new HashMap<>();
    }

    public PerishableObject getTarget(OffensiveObject attacker) {
        List<PerishableObject> potentialTargets = targets.get(attacker.getIdentifier());
        if (potentialTargets != null && !potentialTargets.isEmpty()) {
            return potentialTargets.remove(0);
        }
        return null;
    }

    public Collection<OffensiveObject> getAttackers(PerishableObject target) {
        List<OffensiveObject> potentialAttackers =  attackers.remove(target.getIdentifier());
        if (potentialAttackers != null) {
            return potentialAttackers;
        }
        return Collections.emptyList();
    }

    public void initialize(ItemRoot state) {
        for (Item attacker: state.findAll(OffensiveObject.class::isInstance)) {
            addAttacker((OffensiveObject)attacker);
        }
        for (Item target: state.findAll(PerishableObject.class::isInstance)) {
            addTarget((PerishableObject) target);
        }
    }

    public void update() {
        evaluateMovedObjects();
        evaluateCreatedObjects();
        evaluateRemovedObjects();
    }

    private void evaluateCreatedObjects() {
        for (CreateEvent event: events.getEvents(CreateEvent.class)) {
            Item subject = event.getSubject();

            if (subject instanceof OffensiveObject) {
                addAttacker((OffensiveObject)subject);
            }
            if (subject instanceof PerishableObject) {
                addTarget((PerishableObject)subject);
            }
        }
    }

    private void evaluateMovedObjects() {
        for (MoveEvent event: events.getEvents(MoveEvent.class)) {
            Item subject = event.getSubject();

            if (subject instanceof OffensiveObject) {
                updateAttacker((OffensiveObject)subject);
            }
            if (subject instanceof PerishableObject) {
                updateTarget((PerishableObject)subject);
            }
        }
    }

    private void evaluateRemovedObjects() {
        for (RemoveEvent event: events.getEvents(RemoveEvent.class)) {
            Item subject = event.getSubject();

            if (subject instanceof OffensiveObject) {
                removeAttacker((OffensiveObject)subject);
            }
            if (subject instanceof PerishableObject) {
                removeTarget((PerishableObject)subject);
            }
        }
    }

    private void addAttacker(OffensiveObject attacker) {
        List<PerishableObject> potentials = graph.getTargets(attacker);
        List<PerishableObject> targets = filter(potentials, target -> isAttackable(attacker, target));
        this.targets.put(attacker.getIdentifier(), targets);
    }

    private void addTarget(PerishableObject target) {
        List<OffensiveObject> potentials = graph.getAttackers(target);
        List<OffensiveObject> attackers = filter(potentials, attacker -> isAttackable(attacker, target));
        this.attackers.put(target.getIdentifier(), attackers);
    }

    private void removeAttacker(OffensiveObject attacker) {
        attackers.remove(attacker.getIdentifier());
    }

    private void removeTarget(PerishableObject target) {
        targets.remove(target.getIdentifier());
    }

    private void updateAttacker(OffensiveObject attacker) {
        removeAttacker(attacker);
        addAttacker(attacker);
    }

    private void updateTarget(PerishableObject target) {
        removeTarget(target);
        addTarget(target);
    }

    private boolean isAttackable(Item attacker, Item target) {
        return isAlive(target) && isAnotherTeam(attacker, target) && !isNeutral(target);
    }
}
