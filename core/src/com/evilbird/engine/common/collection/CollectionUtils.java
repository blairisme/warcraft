/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Instances of this class provide helper functions for working with
 * Collections.
 *
 * @author Blair Butterworth
 */
public class CollectionUtils
{
    private CollectionUtils() {
    }

    public static <T> T findFirst(Collection<? extends T> collection, Predicate<T> condition) {
        return collection.stream().filter(condition).findFirst().orElse(null);
    }

    public static <T> int testMatches(T[] collection, Predicate<T> condition) {
        int result = 0;
        for (T element: collection) {
            if (condition.test(element)) {
                result++;
            }
        }
        return result;
    }
}
