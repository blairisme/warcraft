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

import java.util.function.Predicate;

/**
 * Instances of this {@link Action} execute a delegate action only if a given
 * {@link Predicate requirement} is satisfied.
 *
 * @author Blair Butterworth
 */
public class RequirementAction extends DelegateAction
{
    private Predicate<Action> requirement;

    @SerializedConstructor
    private RequirementAction() {
    }

    public RequirementAction(Action delegate, Predicate<Action> requirement) {
        super(delegate);
        this.requirement = requirement;
    }

    @Override
    public boolean act(float delta) {
        if (! requirement.test(delegate)) {
            //cancel();
            return true;
        }
        return delegate.act(delta);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        RequirementAction that = (RequirementAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(requirement, that.requirement)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(requirement)
            .toHashCode();
    }
}
