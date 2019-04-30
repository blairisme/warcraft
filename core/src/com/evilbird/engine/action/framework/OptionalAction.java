/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;

import java.util.function.Predicate;

/**
 * Represents an {@link Action} whose that will only be invoked if a given
 * condition evaluates to {@code true}.
 *
 * @author Blair Butterworth
 */
public class OptionalAction extends BranchAction
{
    public OptionalAction(Action action, Predicate<Action> condition) {
        super(condition, action, new EmptyAction());
    }
}
