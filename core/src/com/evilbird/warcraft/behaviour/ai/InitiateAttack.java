/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.Collection;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.item.utility.ItemOperations.isIdle;
import static com.evilbird.warcraft.item.common.query.UnitOperations.inSight;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isHuman;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAi;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCombatant;

/**
 * Instances of this {@link AiProcedure} instruct AI combatants to attack an
 * enemy if they move nearby.
 *
 * @author Blair Butterworth
 */
public class InitiateAttack implements AiProcedure
{
    private EventQueue events;
    private ActionFactory actions;
    private Collection<Item> aiCombatants;
    private Predicate<Item> isAiCombatant;

    @Inject
    public InitiateAttack(ActionFactory actions, EventQueue events) {
        this.actions = actions;
        this.events = events;
        this.isAiCombatant = both(isCombatant(), isAi());
    }

    @Override
    public void update(ItemRoot gameState) {
        evaluateCache(gameState);
        evaluateAttack();
    }

    private void evaluateCache(ItemRoot gameState) {
        if (aiCombatants == null) {
            initializeCache(gameState);
        } else {
            updateCache();
        }
    }

    private void initializeCache(ItemRoot gameState) {
        aiCombatants = gameState.findAll(isAiCombatant);
    }

    private void updateCache() {
        for (CreateEvent event : events.getEvents(CreateEvent.class)) {
            if (isAiCombatant.test(event.getSubject())) {
                aiCombatants.add(event.getSubject());
            }
        }
        for (RemoveEvent event : events.getEvents(RemoveEvent.class)) {
            aiCombatants.remove(event.getSubject());
        }
    }

    private void evaluateAttack() {
        for (MoveEvent event: events.getEvents(MoveEvent.class)) {
            if (isHuman(event.getSubject())) {
                initiateAttack(event.getSubject());
            }
        }
    }

    private void initiateAttack(Item target) {
        for (Item aiCombatant: aiCombatants) {
            Combatant combatant = (Combatant)aiCombatant;
            if (isIdle(combatant) && inSight(combatant, target)) {
                attack(combatant, target);
            }
        }
    }

    private void attack(Combatant combatant, Item target) {
        Action action = actions.newAction(AttackActions.AttackMelee);
        action.setTarget(target);
        combatant.addAction(action);
    }
}
