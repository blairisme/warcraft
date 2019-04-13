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
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.common.function.Predicates.not;
import static com.evilbird.engine.item.utility.ItemPredicates.*;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAi;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.unit.combatant.CombatantPredicates.withinSight;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Instances of this {@link AiProcedure} instruct AI combatants to attack an
 * enemy if they move nearby.
 *
 * @author Blair Butterworth
 */
public class InitiateAttack extends PeriodicProcedure
{
    private ActionFactory actionFactory;

    @Inject
    public InitiateAttack(ActionFactory actionFactory) {
        super(1, SECONDS);
        this.actionFactory = actionFactory;
    }

    @Override
    public void periodicUpdate(ItemRoot gameState) {
        Collection<Combatant> combatants = getCombatants(gameState);
        for (Combatant combatant: getAvailableCombatants(combatants)) {
            Combatant enemy = getAvailableEnemy(combatants, combatant);
            if (enemy != null) {
                attack(combatant, enemy);
            }
        }
    }

    private Collection<Combatant> getCombatants(ItemRoot gameState) {
        Collection<Combatant> combatants = gameState.findAll(itemWithClass(Combatant.class));
        return CollectionUtils.retain(combatants, isAlive());
    }

    private Collection<Combatant> getAvailableCombatants(Collection<Combatant> combatants) {
        return CollectionUtils.retain(combatants, both(isIdle(), isAi()));
    }

    private Combatant getAvailableEnemy(Collection<Combatant> combatants, Combatant combatant) {
        Item player = combatant.getParent();
        return CollectionUtils.find(combatants, both(not(isOwnedBy(player)), withinSight(combatant)));
    }

    private void attack(Combatant combatant, Item target) {
        Action action = actionFactory.newAction(AttackActions.AttackMelee);
        action.setTarget(target);
        combatant.addAction(action);
    }
}
