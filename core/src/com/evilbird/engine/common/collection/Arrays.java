/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
