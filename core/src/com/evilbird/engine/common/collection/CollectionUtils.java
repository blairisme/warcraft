/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

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
        for (T element: collection) {
            if (condition.test(element)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean containsAll(Collection<T> collection, Predicate<T> condition) {
        for (T element: collection) {
            if (!condition.test(element)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean containsEqual(Collection<T> collection, Predicate<T> conditionA, Predicate<T> conditionB){
        Collection<T> matchesA = filter(collection, conditionA);
        Collection<T> matchesB = filter(collection, conditionB);
        return matchesA.size() == matchesB.size();
    }

    public static <T> List<T> combine(Collection<T> collectionA, Collection<T> collectionB) {
        List<T> result = new ArrayList<>(collectionA.size() + collectionB.size());
        result.addAll(collectionA);
        result.addAll(collectionB);
        return result;
    }

    public static <A, B> List<B> convert(Iterable<A> collection, Function<A, B> converter) {
        List<B> result = new ArrayList<>();
        for (A element: collection) {
            result.add(converter.apply(element));
        }
        return result;
    }

    public static <T> List<T> delta(Collection<T> collectionA, Collection<T> collectionB) {
        List<T> result = new ArrayList<>(collectionA);
        result.removeAll(collectionB);
        return result;
    }

    public static <T> T findFirst(Iterable<? extends T> collection, Predicate<T> condition) {
        for (T element: collection) {
            if (condition.test(element)) {
                return element;
            }
        }
        return null;
    }

    /**
     * Returns a list consisting of the elements of the given collection that
     * match the given predicate.
     */
    public static <T> List<T> filter(Iterable<T> collection, Predicate<T> condition) {
        List<T> result = new ArrayList<>();
        for (T element: collection) {
            if (condition.test(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public static <T> T first(Collection<T> collection) {
        return collection.iterator().next();
    }

    public static <A, B> List<B> flatten(Collection<A> collection, Function<A, Collection<B>> converter) {
        List<B> result = new ArrayList<>(collection.size());
        for (A element: collection) {
            result.addAll(converter.apply(element));
        }
        return result;
    }

    public static <A> List<A> flatten(Collection<? extends Iterable<A>> collection) {
        List<A> result = new ArrayList<>(collection.size());
        for (Iterable<A> array: collection) {
            for (A element: array) {
                result.add(element);
            }
        }
        return result;
    }

    public static <T> void forEach(Collection<T> collection, Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : collection) {
            action.accept(t);
        }
    }

    public static <T> boolean removeIf(Collection<T> collection, Predicate<? super T> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<T> each = collection.iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }

    public static <T> boolean retainIf(Collection<T> collection, Predicate<? super T> condition) {
        return removeIf(collection, condition.negate());
    }

    public static <T> Collection<T> restrictSize(Collection<T> collection, int size) {
        if (collection.size() > size) {
            Iterator<T> iterator = collection.iterator();
            return Arrays.asList(iterator.next(), iterator.next());
        }
        return collection;
    }
}
