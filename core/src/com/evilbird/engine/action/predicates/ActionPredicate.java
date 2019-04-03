/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.predicates;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionTarget;
import com.evilbird.engine.item.Item;

import java.util.function.Predicate;

public class ActionPredicate implements Predicate<Action>
{
    private ActionTarget target;
    private Predicate<? super Item> predicate;

    public ActionPredicate(Predicate<? super Item> predicate, ActionTarget target) {
        this.target = target;
        this.predicate = predicate;
    }

    @Override
    public boolean test(Action action) {
        switch (target) {
            case Item: return predicate.test(action.getItem());
            case Target: return predicate.test(action.getTarget());
            default: throw new UnsupportedOperationException();
        }
    }
}
