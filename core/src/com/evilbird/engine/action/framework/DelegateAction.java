/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;

/**
 * Instances of this {@link Action} provide the basic implementation of an
 * <code>Action</code> which defers its operation to another <code>Action</code>.
 *
 * @author Blair Butterworth
 */
public class DelegateAction extends BasicAction
{
    protected Action delegate;

    public DelegateAction() {
    }

    public DelegateAction(Action delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean act(float delta) {
        if (delegate != null) {
            return delegate.act(delta);
        }
        return true;
    }

    @Override
    public void setIdentifier(Identifier identifier) {
        if (delegate != null) {
            delegate.setIdentifier(identifier);
        }
    }

    @Override
    public void restart() {
        super.restart();
        if (delegate != null) {
            delegate.restart();
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (delegate != null) {
            delegate.reset();
        }
    }

    @Override
    public void setItem(Item item) {
        super.setItem(item);
        if (delegate != null) {
            delegate.setItem(item);
        }
    }

    @Override
    public void setTarget(Item target) {
        super.setTarget(target);
        if (delegate != null) {
            delegate.setTarget(target);
        }
    }

    @Override
    public Throwable getError() {
        return delegate.getError();
    }

    @Override
    public void setError(Throwable error) {
        delegate.setError(error);
    }
}
