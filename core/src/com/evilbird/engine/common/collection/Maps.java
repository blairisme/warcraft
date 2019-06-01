/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import org.apache.commons.lang3.Validate;

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
        Validate.isTrue(values.length > 0);
        Validate.isTrue(values.length % 2 == 0);

        Map<K, V> result = new HashMap<>(values.length / 2);
        for (int i = 0; i < values.length; i += 2) {
            result.put((K)values[i], (V)values[i+1]);
        }
        return result;
    }
}
