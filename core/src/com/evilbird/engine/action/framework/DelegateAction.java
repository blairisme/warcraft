/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionException;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import com.evilbird.engine.common.serialization.SerializedInitializer;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.object.GameObject;
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

    @SerializedConstructor
    private DelegateAction() {
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
    public void setSubject(GameObject gameObject) {
        super.setSubject(gameObject);
        if (delegate != null) {
            delegate.setSubject(gameObject);
        }
    }

    @Override
    public void setTarget(GameObject target) {
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
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

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
            basicDelegate.setSubjectReference(getItemReference());
            basicDelegate.setTargetReference(getTargetReference());

        } else {
            delegate.setTarget(getTarget());
            delegate.setSubject(getSubject());
        }
    }
}
