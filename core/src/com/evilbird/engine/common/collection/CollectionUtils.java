/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import com.badlogic.gdx.utils.Array;

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

    public static <T> T findFirst(Collection<? extends T> collection, Predicate<T> condition) {
        for (T element: collection) {
            if (condition.test(element)) {
                return element;
            }
        }
        return null;
    }

    public static <A, B> List<B> flatten(Collection<A> collection, Function<A, Collection<B>> converter) {
        List<B> result = new ArrayList<>(collection.size());
        for (A element: collection) {
            result.addAll(converter.apply(element));
        }
        return result;
    }

    public static <A> List<A> flatten(Collection<Array<A>> collection) {
        List<A> result = new ArrayList<>(collection.size());
        for (Array<A> array: collection) {
            for (A element: array) {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * Returns a list consisting of the elements of the given collection that
     * match the given predicate.
     */
    public static <T> List<T> filter(Collection<T> collection, Predicate<T> condition) {
        List<T> result = new ArrayList<>();
        for (T element: collection) {
            if (condition.test(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public static <A, B> List<B> convert(Collection<A> collection, Function<A, B> converter) {
        List<B> result = new ArrayList<>();
        for (A element: collection) {
            result.add(converter.apply(element));
        }
        return result;
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

    public static <T> Collection<T> restrictSize(Collection<T> collection, int size) {
        if (collection.size() > size) {
            Iterator<T> iterator = collection.iterator();
            return Arrays.asList(iterator.next(), iterator.next());
        }
        return collection;
    }
}
