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
import com.evilbird.engine.action.ActionException;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.SerializedInitializer;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Action} provide the basic implementation of an
 * {@code Action} which defers its operation to another {@code Action}.
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
        super.setIdentifier(identifier);
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
    public void setCause(UserInput cause) {
        super.setCause(cause);
        if (delegate != null) {
            delegate.setCause(cause);
        }
    }

    @Override
    public ActionException getError() {
        return delegate.getError();
    }

    @Override
    public void setError(ActionException error) {
        delegate.setError(error);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("delegate", delegate)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        DelegateAction that = (DelegateAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(delegate, that.delegate)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(delegate)
            .toHashCode();
    }

    @SerializedInitializer
    protected void updateDelegate() {
        delegate.setIdentifier(getIdentifier());
        delegate.setCause(getCause());

        if (delegate instanceof BasicAction) {
            BasicAction basicDelegate = (BasicAction)delegate;
            basicDelegate.setItemReference(getItemReference());
            basicDelegate.setTargetReference(getTargetReference());

        } else {
            delegate.setTarget(getTarget());
            delegate.setItem(getItem());
        }
    }
}
