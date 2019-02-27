/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.utilities;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.function.ResettablePredicate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Defines common {@link Predicate Predicates} that operate on
 * {@link Action BasicActions}.
 *
 * @author Blair Butterworth
 */
public class ActionPredicates
{
    public static Predicate<Action> noError() {
        return (value) -> !value.hasError();
    }

    public static class NoError implements Predicate<Action> {
        public boolean test(Action value) {
            return !value.hasError();
        }
    }

    public static Predicate<Action> counter(int times) {
        return new Counter(times);
    }

    public static class Counter implements ResettablePredicate<Action> {
        private int times;
        private int count;

        public Counter() {
            this(0);
        }

        public Counter(int times) {
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

            Counter counter = (Counter)obj;
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
}
