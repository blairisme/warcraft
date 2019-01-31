/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantPredicates;

/**
 * Instances of this class provide common {@link Predicate Predicates} for
 * operating on {@link Action Actions}.
 *
 * @author Blair Butterworth
 */
public class ActionPredicates
{
    private ActionPredicates() {
        throw new UnsupportedOperationException();
    }

    public static Predicate<Action> withinRange(Combatant attacker, Item target) {
        return actionPredicate(CombatantPredicates.withinRange(attacker), target);
    }

    public static Predicate<Action> actionPredicate(final Predicate<Item> delegate, final Item item) {
        return (value) -> delegate.test(item);
    }
}
