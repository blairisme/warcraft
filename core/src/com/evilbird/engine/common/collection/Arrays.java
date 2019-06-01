/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import com.badlogic.gdx.utils.Array;

import java.util.function.Predicate;

/**
 * Instances of this class provide helper functions for working with LibGDX
 * {@link Array Arrays}.
 *
 * @author Blair Butterworth
 */
public class Arrays
{
    private static final UnmodifiableArray EMPTY_ARRAY = new UnmodifiableArray();

    /**
     * Disable construction of this static utility class.
     */
    private Arrays() {
    }

    /**
     * Returns an empty LibGDX Array (immutable).
     *
     * @param <T> type of elements, if there were any, in the array.
     *
     * @return an empty immutable array.
     */
    @SuppressWarnings("unchecked")
    public static <T> Array<T> emptyArray() {
        return (Array<T>)EMPTY_ARRAY;
    }

    public static <T> Array<T> retain(Array<T> array, Predicate<T> predicate) {
        Array<T> result = new Array<>(array.size);
        for (T element: array) {
            if (predicate.test(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public static <T> Array<T> union(Array<T> collectionA, Array<T> collectionB) {
        Array<T> result = new Array<>(collectionA.size + collectionB.size);
        result.addAll(collectionA);
        result.addAll(collectionB);
        return result;
    }
}
