package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.framework.duration.ActionDuration;
import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.common.function.Suppliers;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class DelayedAction extends BasicAction
{
    private ActionDuration delay;
    private Supplier<Boolean> validator;

    public DelayedAction(ActionDuration delay) {
        this(delay, Suppliers.isTrue());
    }

    public DelayedAction(ActionDuration delay, Supplier<Boolean> validator) {
        this.delay = delay;
        this.validator = validator;
    }

    @Override
    public boolean act(float delta) {
        if (validator.get() == Boolean.TRUE) {
            return delay.isComplete(delta);
        }
        return true;
    }

    @Override
    public void restart() {
        delay.restart();
    }
}
