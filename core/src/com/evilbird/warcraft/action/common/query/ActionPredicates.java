/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.query;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.function.ResettablePredicate;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantPredicates;
import com.evilbird.warcraft.item.unit.resource.ResourceType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Instances of this class provide common {@link Predicate Predicates} for
 * operating on {@link Action Actions}.
 *
 * @author Blair Butterworth
 */
//TODO: tidy
public class ActionPredicates
{
    private ActionPredicates() {
        throw new UnsupportedOperationException();
    }

    public static Predicate<Action> withinRange() {
        return new WithinRange();
    }

    private static class WithinRange implements Predicate<Action> {
        @Override
        public boolean test(Action value) {
            Combatant combatant = (Combatant)value.getItem();
            Item target = value.getTarget();

            Predicate<Item> predicate = CombatantPredicates.withinRange(combatant);
            return predicate.test(target);
        }
    }

    public static Predicate<Action> withinRange(Combatant attacker, Item target) {
        return actionPredicate(CombatantPredicates.withinRange(attacker), target);
    }

    public static Predicate<Action> actionPredicate(final Predicate<Item> delegate, final Item item) {
        return (value) -> delegate.test(item);
    }

    public static Predicate<Action> isTargetAlive() {
        return (action) -> {
            Unit unit = (Unit)action.getTarget();
            return unit.isAlive();
        };
    }

    public static Predicate<Action> targetHasResources(final ResourceType type) {
        return (action) -> {
            ResourceContainer container = (ResourceContainer)action.getItem();
            return container.getResource(type) > 0;
        };
    }
}
