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
import com.evilbird.engine.item.Item;

import java.util.function.Predicate;

/**
 * Instances of this class provide common {@link Predicate Predicates} for
 * operating on {@link Action Actions}.
 *
 * @author Blair Butterworth
 */
public class ActionPredicates
{
    private ActionPredicates() {
    }

    public static Predicate<Action> item(Predicate<Item> condition) {
        return (action) -> condition.test(action.getItem());
    }

    public static Predicate<Action> target(Predicate<Item> condition) {
        return (action) -> condition.test(action.getTarget());
    }
}
