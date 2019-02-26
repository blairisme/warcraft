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

/**
 * Instances of this {@link Action} execute one of pair of actions based on a
 * given {@link Predicate}.
 *
 * @author Blair Butterworth
 */
public class BranchAction extends CompositeAction
{
    private Action delegate;
    private Predicate<Action> decision;

    public BranchAction(Predicate<Action> decision, Action trueAction, Action falseAction) {
        this.decision = decision;
        actions.add(trueAction);
        actions.add(falseAction);
    }

    @Override
    public boolean act(float delta) {
        if (delegate == null) {
            delegate = decision.test(this) ? actions.get(0) : actions.get(1);
        }
        return delegate.act(delta);
    }

    @Override
    public void restart() {
        super.restart();
        delegate = null;
    }
}
