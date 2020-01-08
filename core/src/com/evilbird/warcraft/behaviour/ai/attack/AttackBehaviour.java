/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.attack.AttackEvent;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.move.MoveActions;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.behaviour.ai.AiBehaviourElement;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;

import javax.inject.Inject;
import java.util.function.Predicate;

import static com.evilbird.engine.object.utility.GameObjectOperations.hasAction;
import static com.evilbird.engine.object.utility.GameObjectOperations.isIdle;

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
    public void applyBehaviour(GameObjectContainer state, float time) {
        initialize(state);
        update(time);
        assignTargets();
        assignAttackers();
    }

    private void initialize(GameObjectContainer state) {
        if (graph == null) {
            graph = new AttackGraph(state, events);
        }
        if (controller == null) {
            controller = new AttackController(events, graph);
        }
        for (GameObject attacker: state.findAll(OffensiveObject.class::isInstance)) {
            assignTarget((OffensiveObject)attacker);
        }
    }

    private void update(float time) {
        graph.update();
        controller.update(time);
    }

    private void assignTargets() {
        for (AttackEvent event: events.getEvents(AttackEvent.class)) {
            GameObject attacker = event.getSubject();
            if (event.isFinished() && isIdle(attacker, AttackActions.class)) {
                assignTarget(attacker);
            }
        }
        for (MoveEvent event: events.getEvents(MoveEvent.class)) {
            GameObject attacker = event.getSubject();
            if (event.isFinished() && isIdle(attacker, MoveActions.class)) {
                assignTarget(attacker);
            }
        }
        for (CreateEvent event: events.getEvents(CreateEvent.class)) {
            assignTarget(event.getSubject());
        }
    }

    private void assignTarget(GameObject gameObject) {
        if (gameObject instanceof OffensiveObject) {
            assignTarget((OffensiveObject) gameObject);
        }
    }

    private void assignTarget(OffensiveObject attacker) {
        for (PerishableObject target: controller.getTargets(attacker)) {
            attack(attacker, target);
        }
    }

    private void assignAttackers() {
        for (MoveEvent event: events.getEvents(MoveEvent.class)) {
            assignAttacker(event.getSubject());
        }
    }

    private void assignAttacker(GameObject target) {
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
        if (!hasAction(attacker, actionClassifier(attacker, target))) {
            Action action = actions.get(AttackActions.Attack);
            action.setSubject(attacker);
            action.setTarget(target);
            attacker.addAction(action);
        }
    }

    private Predicate<Action> actionClassifier(OffensiveObject attacker, PerishableObject target) {
        switch (attacker.getAttackPlurality()) {
            case Individual: return individualAttack(attacker);
            case Multiple: return multipleAttack(target);
            default: throw new UnsupportedOperationException();
        }
    }

    private Predicate<Action> individualAttack(OffensiveObject attacker) {
        Identifier identifier = attacker.getIdentifier();
        return action -> action.getIdentifier() == identifier;
    }

    private Predicate<Action> multipleAttack(PerishableObject target) {
        Identifier identifier = target.getIdentifier();
        return action -> action.getTarget().getIdentifier() == identifier;
    }
}