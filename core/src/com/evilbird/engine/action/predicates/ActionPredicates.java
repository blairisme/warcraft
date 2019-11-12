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
import com.evilbird.engine.object.GameObject;

import java.util.function.Predicate;

/**
 * Defines common {@link Predicate Predicates} that operate on
 * {@link Action BasicActions}.
 *
 * @author Blair Butterworth
 */
public class ActionPredicates
{
    private ActionPredicates() {
    }

    public static Predicate<Action> item(Predicate<GameObject> condition) {
        return action -> condition.test(action.getSubject());
    }
}
