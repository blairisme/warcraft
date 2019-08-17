/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.attack.AttackEvent;
import com.evilbird.warcraft.action.move.MoveActions;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.behaviour.ai.AiBehaviourElement;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.engine.item.utility.ItemOperations.hasAction;
import static com.evilbird.engine.item.utility.ItemOperations.isIdle;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isAlive;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isAnotherTeam;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isAttacker;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isUnit;

/**
 * Instances of this {@link AiBehaviourElement} instruct combatants to attack an
 * enemy if they move nearby or once they finishing attacking or moving.
 *
 * @author Blair Butterworth
 */
public class AttackBehaviour implements AiBehaviourElement
{
    private EventQueue events;
    private ActionFactory actions;
    private AttackGraph graph;
    private AttackGraphUpdater graphUpdater;

    @Inject
    public AttackBehaviour(ActionFactory actions, EventQueue events) {
        this.actions = actions;
        this.events = events;
    }

    @Override
    public void applyBehaviour(ItemRoot root) {
        updateGraph(root);
        assignTargets();
        assignAttackers();
    }

    private void updateGraph(ItemRoot root) {
        if (graph == null) {
            graph = new AttackGraph(root.getSpatialGraph());
            graphUpdater = new AttackGraphUpdater(graph, events);
            graphUpdater.initialize(root);
        }
        graphUpdater.update();
    }

    private void assignTargets() {
        for (AttackEvent event: events.getEvents(AttackEvent.class)) {
            Item attacker = event.getSubject();
            if (event.isFinished() && isIdle(attacker, AttackActions.class)) {
                assignTarget((Combatant)attacker);
            }
        }
        for (MoveEvent event: events.getEvents(MoveEvent.class)) {
            Item attacker = event.getSubject();
            if (event.isFinished() && isAttacker(attacker) && isIdle(attacker, MoveActions.class)) {
                assignTarget((Combatant)attacker);
            }
        }
    }

    private void assignTarget(Combatant combatant) {
        for (Item target : graph.getAttackTargets(combatant)) {
            if (isAlive(target) && isAnotherTeam(combatant, target)) {
                attack(combatant, target);
                return;
            }
        }
    }

    private void assignAttackers() {
        for (MoveEvent event: events.getEvents(MoveEvent.class)) {
            Item subject = event.getSubject();
            if (isUnit(subject)){
                assignAttacker(subject, event.getLocation());
            }
        }
    }

    private void assignAttacker(Item target, ItemNode location) {
        for (Combatant attacker: graph.getAttackers(location)) {
            if (isIdle(attacker) && isAlive(attacker) && isAnotherTeam(attacker, target)) {
                attack(attacker, target);
            }
        }
    }

    private void attack(Combatant combatant, Item target) {
        if (!hasAction(combatant, AttackActions.Attack)) {
            Action action = actions.get(AttackActions.Attack);
            action.setItem(combatant);
            action.setTarget(target);
            combatant.addAction(action);
        }
    }
}