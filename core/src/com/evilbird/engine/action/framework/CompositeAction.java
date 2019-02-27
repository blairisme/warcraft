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
import com.evilbird.engine.item.Item;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Instances of this {@link Action Action} provide common implementation
 * for those actions which represent collection of child actions.
 *
 * @author Blair Butterworth
 */
public abstract class CompositeAction extends BasicAction
{
    protected List<Action> actions;

    public CompositeAction() {
        actions = new ArrayList<>();
    }

    public CompositeAction(List<Action> actions) {
        this.actions = new ArrayList<>(actions);
    }

    public CompositeAction(Action... actions) {
        this.actions = new ArrayList<>(actions.length);
        Collections.addAll(this.actions, actions);
    }

    @Override
    public void restart() {
        super.restart();
        for (Action delegate: actions) {
            delegate.restart();
        }
    }

    @Override
    public void reset() {
        super.reset();
        for (Action delegate: actions) {
            delegate.reset();
        }
    }

    @Override
    public void setItem(Item item) {
        super.setItem(item);
        for (Action delegate: actions) {
            delegate.setItem(item);
        }
    }

    @Override
    public void setTarget(Item target) {
        super.setTarget(target);
        for (Action delegate: actions) {
            delegate.setTarget(target);
        }
    }

    @Override
    public ActionException getError() {
        for (Action delegate: actions) {
            if (delegate.hasError()) {
                return delegate.getError();
            }
        }
        return null;
    }

    @Override
    public void setError(ActionException error) {
        for (Action delegate: actions) {
            delegate.setError(error);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("base")
            .append("actions", actions)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        CompositeAction that = (CompositeAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(actions, that.actions)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(actions)
            .toHashCode();
    }
}
