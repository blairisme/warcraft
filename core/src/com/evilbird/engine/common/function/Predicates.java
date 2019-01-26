/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.function;

public class Predicates
{
    public static <T> Predicate<T> both(Predicate<? super T> left, Predicate<? super T> right) {
        return new And(left, right);
    }

    public static <T> Predicate<T> either(Predicate<T> left, Predicate<T> right) {
        return new Or(left, right);
    }

    public static <T> Predicate<T> not(Predicate<? super T> predicate) {
        return new Not(predicate);
    }

    public static class And<T> implements Predicate<T> {
        private Predicate<T> left;
        private Predicate<T> right;

        public And(Predicate<T> left, Predicate<T> right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean test(T value) {
            return left.test(value) && right.test(value);
        }
    }

    public static class Or<T> implements Predicate<T> {
        private Predicate<T> left;
        private Predicate<T> right;

        public Or(Predicate<T> left, Predicate<T> right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean test(T value) {
            return left.test(value) || right.test(value);
        }
    }

    public static class Not<T> implements Predicate<T> {
        private Predicate<T> predicate;

        public Not(Predicate<T> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean test(T value) {
            return !predicate.test(value);
        }
    }
}
