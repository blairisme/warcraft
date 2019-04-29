/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class provide helper functions for working with
 * Maps.
 *
 * @author Blair Butterworth
 */
public class Maps
{
    /**
     * Disable construction of this static utility class.
     */
    private Maps() {
    }

    /**
     * Returns a new {@link Map} containing the given key value pair.
     *
     * @param k1    the key with which the given value is to be associated.
     * @param v1    the value to be associated with the given key.
     * @param <K>   the key type.
     * @param <V>   the value type.
     *
     * @return a new {@code Map} containing the given key value pair.
     */
    public static <K, V> Map<K, V> of(K k1, V v1) {
        Map<K, V> result = new HashMap<>(1);
        result.put(k1, v1);
        return result;
    }

    /**
     * Returns a new {@link Map} containing the given key value pairs.
     *
     * @param k1    the key with which the first value is to be associated.
     * @param v1    the value to be associated with the first key.
     * @param k2    the key with which the second value is to be associated.
     * @param v2    the value to be associated with the second key.
     * @param <K>   the key type.
     * @param <V>   the value type.
     *
     * @return a new {@code Map} containing the given key value pairs.
     */
    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
        Map<K, V> result = new HashMap<>(2);
        result.put(k1, v1);
        result.put(k2, v2);
        return result;
    }

    /**
     * Returns a new {@link Map} containing the given key value pairs.
     *
     * @param k1    the key with which the first value is to be associated.
     * @param v1    the value to be associated with the first key.
     * @param k2    the key with which the second value is to be associated.
     * @param v2    the value to be associated with the second key.
     * @param k3    the key with which the third value is to be associated.
     * @param v3    the value to be associated with the third key.
     * @param <K>   the key type.
     * @param <V>   the value type.
     *
     * @return a new {@code Map} containing the given key value pairs.
     */
    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        Map<K, V> result = new HashMap<>(3);
        result.put(k1, v1);
        result.put(k2, v2);
        result.put(k3, v3);
        return result;
    }

    /**
     * Returns a new {@link Map} containing the given key value pairs.
     *
     * @param k1    the key with which the first value is to be associated.
     * @param v1    the value to be associated with the first key.
     * @param k2    the key with which the second value is to be associated.
     * @param v2    the value to be associated with the second key.
     * @param k3    the key with which the third value is to be associated.
     * @param v3    the value to be associated with the third key.
     * @param k4    the key with which the fourth value is to be associated.
     * @param v4    the value to be associated with the fourth key.
     * @param <K>   the key type.
     * @param <V>   the value type.
     *
     * @return a new {@code Map} containing the given key value pairs.
     */
    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Map<K, V> result = new HashMap<>(4);
        result.put(k1, v1);
        result.put(k2, v2);
        result.put(k3, v3);
        result.put(k4, v4);
        return result;
    }

    /**
     * Returns a new {@link Map} containing the given key value pairs.
     *
     * @param values    a list of key value pairs.
     * @param <K>       the key type.
     * @param <V>       the value type.
     *
     * @return a new {@code Map} containing the given key value pairs.
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> of(Object ... values) {
        Map<K, V> result = new HashMap<>(values.length / 2);
        for (int i = 0; i < values.length; i += 2) {
            result.put((K)values[i], (V)values[i+1]);
        }
        return result;
    }
}
