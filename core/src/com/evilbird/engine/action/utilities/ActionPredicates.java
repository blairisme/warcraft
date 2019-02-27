/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.utilities;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.function.Predicate;

/**
 * Defines common {@link Predicate Predicates} that operate on
 * {@link Action BasicActions}.
 *
 * @author Blair Butterworth
 */
public class ActionPredicates
{
    public static Predicate<Action> noError() {
        return (value) -> !value.hasError();
    }

    public static class NoError implements Predicate<Action> {
        public boolean test(Action value) {
            return !value.hasError();
        }
    }
}
