/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.function;

/**
 * Instances of this class contain common {@link Predicate Predicates}.
 *
 * @author Blair Butterworth
 */
public class Predicates
{
    private Predicates() {
        throw new UnsupportedOperationException();
    }

    public static <T> Predicate<T> accept() {
        return new Accept<>();
    }

    private static class Accept<T> implements Predicate<T> {
        public boolean test(T value) {
            return true;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Accept;
        }
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
}
