/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.predicates;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.function.ResettablePredicate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Instances of this Predicate allow a test to be run a specific number
 * of times.
 *
 * @author Blair Butterworth
 */
public class InvocationCount implements ResettablePredicate<Action>
{
    private int times;
    private int count;

    public InvocationCount() {
        this(0);
    }

    public InvocationCount(int times) {
        this.times = times;
        this.count = 0;
    }

    @Override
    public boolean test(Action value) {
        return count++ < times;
    }

    @Override
    public void reset() {
        count = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        InvocationCount counter = (InvocationCount)obj;
        return new EqualsBuilder()
            .append(times, counter.times)
            .append(count, counter.count)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(times)
            .append(count)
            .toHashCode();
    }
}