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
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.function.Predicates;
import com.evilbird.engine.common.function.ResettablePredicate;

/**
 * Instances of this {@link Action} execute a given action repeatedly, until
 * (optionally) instructed to stop.
 *
 * @author Blair Butterworth
 */
public class RepeatedAction extends DelegateAction
{
    private Predicate<Action> repeat;

    public RepeatedAction(Action action) {
        this(action, Predicates.accept());
    }

    public RepeatedAction(Action action, Predicate<Action> repeat) {
        super(action);
        this.repeat = repeat;
    }

    @Override
    public boolean act(float delta) {
        if (delegate.act(delta)) {
            return repeat();
        }
        return false;
    }

    private boolean repeat() {
        if (repeat.test(this)) {
            delegate.restart();
            return false;
        }
        return true;
    }

    @Override
    public void restart() {
        super.restart();
        if (repeat instanceof ResettablePredicate) {
            ((ResettablePredicate)repeat).reset();
        }
    }
}
