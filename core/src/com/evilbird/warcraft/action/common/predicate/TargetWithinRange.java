/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.predicate;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.utility.ItemPredicates;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

/**
 * Instances of this {@link Predicate} tests the Item and Target contained in a
 * given Action to determine if they are within range of each other.
 *
 * @author Blair Butterworth
 */
@Deprecated
public class TargetWithinRange implements Predicate<Action>
{
    public TargetWithinRange() {
    }

    @Override
    public boolean test(Action value) {
        Combatant combatant = (Combatant)value.getItem();
        Item target = value.getTarget();

        Predicate<Item> predicate = ItemPredicates.isNear(combatant, combatant.getRange());
        return predicate.test(target);
    }
}
