/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.attack.AttackEvent;
import com.evilbird.warcraft.action.move.MoveActions;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.behaviour.ai.AiBehaviourElement;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.Collection;
import java.util.stream.Collectors;

import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.item.common.query.UnitOperations.inSight;

/**
 * Instances of this {@link AiBehaviourElement} instruct AI combatants to attack an
 * enemy if they move nearby.
 *
 * @author Blair Butterworth
 */
public class InitiateAttack implements AiBehaviourElement
{
    private static final int SIGHT_MAX = 6 * TILE_WIDTH;

    private EventQueue events;
    private ActionFactory actions;

    @Inject
    public InitiateAttack(ActionFactory actions, EventQueue events) {
        this.actions = actions;
        this.events = events;
    }

    @Override
    public void update(ItemRoot state) {
        evaluateAttackEvents(state);
        evaluateMoveEvents(state);
    }

    private void evaluateAttackEvents(ItemRoot state) {
        for (AttackEvent event: events.getEvents(AttackEvent.class)) {
            Combatant subject = (Combatant)event.getSubject();
            if (event.isFinished() && isIdle(subject, AttackActions.class)) {
                evaluateCombatant(state, subject);
            }
        }
    }

    private void evaluateMoveEvents(ItemRoot state) {
        for (MoveEvent event: events.getEvents(MoveEvent.class)) {
            Item subject = event.getSubject();
            if (event.isComplete() && isCombatant(subject) && isIdle(subject, MoveActions.class)) {
                evaluateCombatant(state, (Combatant)subject);
            }
            if (isUnit(subject)){
                evaluateTarget(state, subject);
            }
        }
    }

    private void evaluateCombatant(ItemRoot state, Combatant combatant) {
        for (Item target : getItems(state, combatant, combatant.getSight())) {
            if (isUnit(target) && isAnotherTeam(combatant, target) && isAlive(target)) {
                attack(combatant, target);
                return;
            }
        }
    }

    private void evaluateTarget(ItemRoot state, Item target) {
        for (Item item: getItems(state, target, SIGHT_MAX)) {
            if (isCombatant(item) && isAlive(item) && isIdle(item) && isAnotherTeam(item, target)) {
                Combatant combatant = (Combatant)item;
                if (inSight(combatant, target)) {
                    attack(combatant, target);
                }
            }
        }
    }

    private boolean isAnotherTeam(Item itemA, Item itemB) {
        Player playerA = getPlayer(itemA);
        Player playerB = getPlayer(itemB);
        return !playerA.isNeutral() && !playerB.isNeutral() && playerA != playerB;
    }

    private boolean isCombatant(Item item) {
        return item instanceof Combatant;
    }

    private boolean isUnit(Item item) {
        return item instanceof Unit;
    }

    private boolean isAlive(Item item) {
        Unit unit = (Unit)item;
        return unit.getHealth() > 0;
    }

    private boolean isIdle(Item item) {
        return isIdle(item, null);
    }

    private boolean isIdle(Item item, Class<?> allowed) {
        Collection<Action> actions = item.getActions();
        if (actions.isEmpty()) {
            return true;
        }
        if (allowed != null && actions.size() == 1) {
            Action action = actions.iterator().next();
            return action.getIdentifier().getClass().isAssignableFrom(allowed);
        }
        return false;
    }

    private void attack(Combatant combatant, Item target) {
        Action action = actions.newAction(AttackActions.AttackMelee);
        action.setTarget(target);
        combatant.addAction(action);
    }

    private Collection<Item> getItems(ItemRoot state, Item locus, int radius) {
        ItemGraph graph = state.getSpatialGraph();

        Vector2 size = locus.getSize().add(radius, radius);
        Vector2 position = locus.getPosition().sub(radius, radius);

        Collection<ItemNode> nodes = graph.getNodes(position, size);
        return nodes.stream().flatMap(node -> node.getOccupants().stream()).collect(Collectors.toSet());
    }
}