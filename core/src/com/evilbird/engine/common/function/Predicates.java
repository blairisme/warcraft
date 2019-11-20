/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.function;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Instances of this class contain common {@link Predicate Predicates}.
 *
 * @author Blair Butterworth
 */
public class Predicates
{
    private Predicates() {
    }

    public static <T> Predicate<T> isNull() {
        return (value) -> value == null;
    }

    public static <T> Predicate<T> nonNull() {
        return (value) -> value != null;
    }

    public static <T> Predicate<T> accept() {
        return (value) -> true;
    }

    public static <T> Predicate<T> never() {
        return (value) -> false;
    }

    public static <T> Predicate<T> both(Predicate<? super T> left, Predicate<? super T> right) {
        return (value) -> left.test(value) && right.test(value);
    }

    public static <X, Y> BiPredicate<X, Y> both(BiPredicate<X, Y> left, BiPredicate<X, Y> right) {
        return (x, y) -> left.test(x, y) && right.test(x, y);
    }

    public static <T> Predicate<T> either(Predicate<T> left, Predicate<T> right) {
        return (value) -> left.test(value) || right.test(value);
    }

    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }

    @SafeVarargs
    public static <T> Predicate<T> all(final Predicate<T> ... conditions) {
        return value -> {
            for (Predicate<T> condition: conditions) {
                if (! condition.test(value)) {
                    return false;
                }
            }
            return true;
        };
    }

    @SafeVarargs
    public static <X, Y> BiPredicate<X, Y> all(BiPredicate<X, Y> ... conditions) {
        return (a, b) -> {
            for (BiPredicate<X, Y> condition: conditions) {
                if (! condition.test(a, b)) {
                    return false;
                }
            }
            return true;
        };
    }
}
