/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.function;

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

    public static <T> Predicate<T> accept() {
        return (value) -> true;
    }

    public static <T> Predicate<T> never() {
        return (value) -> false;
    }

    public static <T> Predicate<T> both(Predicate<? super T> left, Predicate<? super T> right) {
        return (value) -> left.test(value) && right.test(value);
    }

    public static <T> Predicate<T> either(Predicate<T> left, Predicate<T> right) {
        return (value) -> left.test(value) || right.test(value);
    }

    public static <T> Predicate<T> not(Predicate<? super T> predicate) {
        return (value) -> !predicate.test(value);
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> combination(Predicate<? super T> ... conditions) {
        return (value) -> {
            for (Predicate condition : conditions) {
                if (!condition.test(value)) {
                    return false;
                }
            }
            return true;
        };
    }
}
