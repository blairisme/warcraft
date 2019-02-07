/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.framework.duration.ActionDuration;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.function.Predicates;
import com.evilbird.engine.common.function.Supplier;

/**
 * Instances of this class provide an {@link Action Action} that is only
 * acted upon when indicated to do so by a given {@link Supplier validator}.
 *
 * @author Blair Butterworth
 */
public class DelayedAction extends BasicAction
{
    private ActionDuration delay;
    private Predicate<Action> validator;

    public DelayedAction(ActionDuration delay) {
        this(delay, Predicates.accept());
    }

    public DelayedAction(ActionDuration delay, Predicate<Action> validator) {
        this.delay = delay;
        this.validator = validator;
    }

    @Override
    public boolean act(float delta) {
        if (validator.test(this)) {
            return delay.isComplete(delta);
        }
        return true;
    }

    @Override
    public void restart() {
        delay.restart();
    }
}
