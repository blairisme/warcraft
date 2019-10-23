/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.attack.AttackEvent;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.move.MoveActions;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.behaviour.ai.AiBehaviourElement;
import com.evilbird.warcraft.item.common.capability.OffensiveObject;
import com.evilbird.warcraft.item.common.capability.PerishableObject;

import javax.inject.Inject;

import static com.evilbird.engine.item.utility.ItemOperations.hasAction;
import static com.evilbird.engine.item.utility.ItemOperations.isIdle;

/**
 * Instances of this {@link AiBehaviourElement} instruct combatants to attack an
 * enemy if they move nearby or once they finishing attacking or moving.
 *
 * @author Blair Butterworth
 */
public class AttackBehaviour implements AiBehaviourElement
{
    private Events events;
    private ActionFactory actions;
    private AttackGraph graph;
    private AttackController controller;

    @Inject
    public AttackBehaviour(ActionFactory actions, Events events) {
        this.actions = actions;
        this.events = events;
    }

    @Override
    public void applyBehaviour(ItemRoot state) {
        initialize(state);
        update();
        assignTargets();
        assignAttackers();
    }

    private void initialize(ItemRoot state) {
        if (graph == null) {
            graph = new AttackGraph(state.getSpatialGraph(), events);
            graph.initialize(state);
        }
        if (controller == null) {
            controller = new AttackController(events, graph);
            controller.initialize(state);
        }
        for (Item attacker: state.findAll(OffensiveObject.class::isInstance)) {
            assignTarget((OffensiveObject)attacker);
        }
    }

    private void update() {
        graph.update();
        controller.update();
    }

    private void assignTargets() {
        for (AttackEvent event: events.getEvents(AttackEvent.class)) {
            Item attacker = event.getSubject();
            if (event.isFinished() && isIdle(attacker, AttackActions.class)) {
                assignTarget(attacker);
            }
        }
        for (MoveEvent event: events.getEvents(MoveEvent.class)) {
            Item attacker = event.getSubject();
            if (event.isFinished() && isIdle(attacker, MoveActions.class)) {
                assignTarget(attacker);
            }
        }
        for (CreateEvent event: events.getEvents(CreateEvent.class)) {
            assignTarget(event.getSubject());
        }
    }

    private void assignTarget(Item item) {
        if (item instanceof OffensiveObject) {
            assignTarget((OffensiveObject)item);
        }
    }

    private void assignTarget(OffensiveObject attacker) {
        PerishableObject target = controller.getTarget(attacker);
        if (target != null) {
            attack(attacker, target);
        }
    }

    private void assignAttackers() {
        for (MoveEvent event: events.getEvents(MoveEvent.class)) {
            assignAttacker(event.getSubject());
        }
    }

    private void assignAttacker(Item target) {
        if (target instanceof PerishableObject) {
            assignAttacker((PerishableObject)target);
        }
    }

    private void assignAttacker(PerishableObject target) {
        for (OffensiveObject attacker: controller.getAttackers(target)) {
            attack(attacker, target);
        }
    }

    private void attack(OffensiveObject attacker, PerishableObject target) {
        if (!hasAction(attacker, AttackActions.Attack)) {
            Action action = actions.get(AttackActions.Attack);
            action.setItem(attacker);
            action.setTarget(target);
            attacker.addAction(action);
        }
    }
}