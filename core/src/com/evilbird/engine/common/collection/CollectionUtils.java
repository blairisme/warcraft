/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * Provides utility functions for working with Collections.
 *
 * @author Blair Butterworth
 */
public class CollectionUtils
{
    private CollectionUtils() {
    }

    public static <T> boolean containsAny(Collection<T> collection, Predicate<T> condition) {
        return collection.stream().anyMatch(condition);
    }

    public static <T> boolean containsAll(Collection<T> collection, Predicate<T> condition) {
        return collection.stream().allMatch(condition);
    }

    public static <T> boolean containsEqual(Collection<T> collection, Predicate<T> conditionA, Predicate<T> conditionB){
        Collection<T> matchesA = collection.stream().filter(conditionA).collect(toList());
        Collection<T> matchesB = collection.stream().filter(conditionB).collect(toList());
        return matchesA.size() == matchesB.size();
    }

    public static <T> Collection<T> combine(Collection<T> collectionA, Collection<T> collectionB) {
        Collection<T> result = new ArrayList<>(collectionA.size() + collectionB.size());
        result.addAll(collectionA);
        result.addAll(collectionB);
        return result;
    }

    public static <T> T findFirst(Collection<? extends T> collection, Predicate<T> condition) {
        return collection.stream().filter(condition).findFirst().orElse(null);
    }

    public static <A, B> Collection<B> flatten(Collection<A> collection, Function<A, Collection<B>> converter) {
        return collection.stream().flatMap(element -> converter.apply(element).stream()).collect(toList());
    }

    public static <T> Collection<T> filter(Collection<T> collection, Predicate<T> condition) {
        return collection.stream().filter(condition).collect(toList());
    }

    public static <A, B> Collection<B> convert(Collection<A> collection, Function<A, B> converter) {
        return collection.stream().map(converter).collect(toList());
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
