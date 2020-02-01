/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.function;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Contains common {@link Predicate Predicates}.
 *
 * @author Blair Butterworth
 */
public class Predicates
{
    /**
     * Disable construction of static helper class.
     */
    private Predicates() {
    }

    @SuppressWarnings("Convert2MethodRef") // Replacement not in RoboVM JDK
    public static <T> Predicate<T> isNull() {
        return (value) -> value == null;
    }

    @SuppressWarnings("Convert2MethodRef") // Replacement not in RoboVM JDK
    public static <T> Predicate<T> nonNull() {
        return (value) -> value != null;
    }

    public static <T> Predicate<T> accept() {
        return (value) -> true;
    }

    public static <T> Predicate<T> reject() {
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
