/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.item.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Instances of this {@link Action Action} provide common implementation
 * for those actions which represent collection of child actions.
 *
 * @author Blair Butterworth
 */
public abstract class CompositeAction extends BasicAction
{
    protected List<Action> delegates;

    public CompositeAction() {
        delegates = new ArrayList<>();
    }

    public CompositeAction(List<Action> delegates) {
        this.delegates = delegates;
    }

    public CompositeAction(Action... delegates) {
        this(Arrays.asList(delegates));
    }

    @Override
    public void restart() {
        super.restart();
        for (Action delegate: delegates) {
            delegate.restart();
        }
    }

    @Override
    public void reset() {
        super.reset();
        for (Action delegate: delegates) {
            delegate.reset();
        }
        delegates.clear();
    }

    @Override
    public void setItem(Item item) {
        super.setItem(item);
        for (Action delegate: delegates) {
            delegate.setItem(item);
        }
    }

    @Override
    public void setTarget(Item target) {
        super.setTarget(target);
        for (Action delegate: delegates) {
            delegate.setTarget(target);
        }
    }

    @Override
    public Throwable getError() {
        for (Action delegate: delegates) {
            if (delegate.hasError()) {
                return delegate.getError();
            }
        }
        return null;
    }

    @Override
    public void setError(Throwable error) {
        for (Action delegate: delegates) {
            delegate.setError(error);
        }
    }
}
