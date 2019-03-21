/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import com.evilbird.engine.common.function.Comparator;
import com.evilbird.engine.common.function.Predicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Instances of this class provide helper functions for working with
 * Collections.
 *
 * @author Blair Butterworth
 */
//TODO: Rename to CollectionUtils or Replace with Guava
public class Collections
{
    private Collections() {
        throw new UnsupportedOperationException();
    }

    public static <T> T find(Collection<T> collection, Predicate<? super T> predicate) {
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            final T element = iterator.next();
            if (predicate.test(element)) {
                return element;
            }
        }
        return null;
    }

    public static <T> T min(Collection<? extends T> collection, Comparator<? super T> comparator) {
        Iterator<? extends T> iterator = collection.iterator();
        T result = iterator.next();

        while (iterator.hasNext()) {
            T other = iterator.next();

            if (comparator.compare(other, result) < 0) {
                result = other;
            }
        }
        return result;
    }

    public static <T> Collection<T> retain(Collection<? extends T> collection, Predicate<? super T> predicate) {
        Collection<T> result = new ArrayList<>(collection.size());
        for (T element: collection) {
            if (predicate.test(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public static <T> List<T> union(List<T> collectionA, List<T> collectionB) {
        List<T> result = new ArrayList<>(collectionA.size() + collectionB.size());
        result.addAll(collectionA);
        result.addAll(collectionB);
        return result;
    }
}
