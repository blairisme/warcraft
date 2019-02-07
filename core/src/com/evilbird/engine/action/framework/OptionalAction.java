/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.common.function.Predicate;

public class OptionalAction extends BranchAction
{
    public OptionalAction(Predicate<Action> decision, Action trueAction) {
        super(decision, trueAction, new EmptyAction());
    }
}
