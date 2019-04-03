/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.evilbird.engine.item.Item;

import java.util.function.Predicate;

import static com.evilbird.engine.item.utility.ItemPredicates.isNear;

/**
 * Instances of this class provide common {@link Predicate Predicates} that
 * operate on {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class CombatantPredicates
{
    private CombatantPredicates() {
        throw new UnsupportedOperationException();
    }

    public static Predicate<Item> withinSight(Combatant combatant) {
        return isNear(combatant, combatant.getSight());
    }

    public static Predicate<Item> withinRange(Combatant combatant) {
        return isNear(combatant, combatant.getRange());
    }
}
