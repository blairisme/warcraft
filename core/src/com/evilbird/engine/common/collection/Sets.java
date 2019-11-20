/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
//import java.util.stream.Collectors;

/**
 * This class provides utility functions that operate on {@link Set Sets}.
 *
 * @author Blair Butterworth
 */
public class Sets
{
    /**
     * Disable construction of static helper class.
     */
    private Sets() {
    }

    /**
     * Returns a new {@link Set} containing the given values.
     *
     * @param elements  an array of values.
     * @param <T>       the type of elements in the set.
     *
     * @return a new {@code Set} containing the given values.
     */
    public static <T> Set<T> of(T ... elements) {
        Objects.requireNonNull(elements);
        Set<T> set = new HashSet<>();
        for (T element: elements) {
            set.add(element);
        }
        return set;
    }
}
