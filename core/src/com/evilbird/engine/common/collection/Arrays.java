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
import com.evilbird.engine.common.function.Predicate;

/**
 * Instances of this class provide helper functions for working with Arrays.
 *
 * @author Blair Butterworth
 */
public class Arrays
{
    private static final UnmodifiableArray EMPTY_ARRAY = new UnmodifiableArray();

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
}
