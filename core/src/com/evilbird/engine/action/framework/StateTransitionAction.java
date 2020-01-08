/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;

/**
 * An {@link Action} implementation which delegates its operation to child
 * actions based on the state of the actions subject or target.
 *
 * @author Blair Butterworth
 */
public abstract class StateTransitionAction extends CompositeAction
{
    private transient Action current;
    private transient Action previous;

    public StateTransitionAction() {
    }

    public StateTransitionAction(Action... actions) {
        super(actions);
    }

    @Override
    public boolean act(float time) {
        if (current == null) {
            current = nextAction(previous);
            if (current == null) {
                return ActionComplete;
            }
        }
        if (current.act(time)) {
            if (current.hasError()) {
                if (isCriticalError(current)) {
                    return ActionComplete;
                }
            }
            current.restart();
            previous = current;
            current = null;
        }
        return ActionIncomplete;
    }

    @Override
    public void reset() {
        super.reset();
        current = null;
        previous = null;
    }

    @Override
    public void restart() {
        super.restart();
        current = null;
        previous = null;
    }

    protected abstract Action nextAction(Action previous);

    protected boolean isCriticalError(Action action) {
        return true;
    }
}
