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
import com.evilbird.engine.common.function.Predicates;
import com.evilbird.engine.common.function.ResettablePredicate;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.function.Predicate;

/**
 * Instances of this {@link Action} execute a given action repeatedly, until
 * (optionally) instructed to stop.
 *
 * @author Blair Butterworth
 */
public class RepeatedAction extends DelegateAction
{
    private Predicate<Action> repeat;

    @SerializedConstructor
    private RepeatedAction() {
    }

    public RepeatedAction(Action action) {
        this(action, Predicates.accept());
    }

    public RepeatedAction(Action action, Predicate<Action> repeat) {
        super(action);
        this.repeat = repeat;
    }

    @Override
    public boolean act(float delta) {
        if (delegate.act(delta)) {
            return repeat();
        }
        return false;
    }

    private boolean repeat() {
        if (repeat.test(this)) {
            delegate.restart();
            return false;
        }
        return true;
    }

    @Override
    public void restart() {
        super.restart();
        if (repeat instanceof ResettablePredicate) {
            ((ResettablePredicate)repeat).reset();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        RepeatedAction that = (RepeatedAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(repeat, that.repeat)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(repeat)
            .toHashCode();
    }
}
