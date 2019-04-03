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
import com.evilbird.engine.common.serialization.SerializedConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.function.Predicate;

/**
 * Instances of this {@link Action} execute one of pair of actions based on a
 * given {@link Predicate}.
 *
 * @author Blair Butterworth
 */
public class BranchAction extends CompositeAction
{
    private transient Action delegate;
    private Predicate<Action> decision;

    @SerializedConstructor
    private BranchAction() {
    }

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("delegate", delegate)
            .append("decision", decision)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        BranchAction that = (BranchAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(decision, that.decision)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(decision)
            .toHashCode();
    }
}
