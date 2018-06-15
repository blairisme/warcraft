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
    public static <K, V> Map<K, V> of (K k1, V v1) {
        Map<K, V> result = new HashMap<>(1);
        result.put(k1, v1);
        return result;
    }

    public static <K, V> Map<K, V> of (K k1, V v1, K k2, V v2) {
        Map<K, V> result = new HashMap<>(2);
        result.put(k1, v1);
        result.put(k2, v2);
        return result;
    }

    public static <K, V> Map<K, V> of (K k1, V v1, K k2, V v2, K k3, V v3) {
        Map<K, V> result = new HashMap<>(2);
        result.put(k1, v1);
        result.put(k2, v2);
        result.put(k3, v3);
        return result;
    }
}
