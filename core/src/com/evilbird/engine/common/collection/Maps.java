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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toMap;

/**
 * This class provides utility functions that operate on {@link Map Maps}.
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

    /**
     * Returns a {@link Collection} containing the values associated with the
     * given keys.
     *
     * @param map   a {@link Map} whose values will be returned.
     * @param keys  the keys associated with the desired values.
     * @param <K>   the key type.
     * @param <V>   the value type.
     *
     * @return a {@link Collection} containing the desired values.
     */
    public static <K, V> List<V> getAll(Map<K, V> map, List<K> keys) {
        List<V> result = new ArrayList<>(keys.size());
        for (Object key: keys) {
            result.add(map.get(key));
        }
        return result;
    }

    /**
     * Returns the value to which the specified key is mapped, or uses the
     * given {@link Supplier} to create a new value if this map contains no
     * mapping for the key.
     *
     * @param map               a {@link Map} whose values will be returned.
     * @param key               the key whose associated value is to be
     *                          returned.
     * @param defaultSupplier   create a new default value.
     * @param <K>               the key type.
     * @param <V>               the value type.
     *
     * @return  the value to which the specified key is mapped, or a default
     *          value.
     */
    public static <K, V> V getOrDefault(Map<K, V> map, K key, Supplier<V> defaultSupplier) {
        if (!map.containsKey(key)) {
            return defaultSupplier.get();
        }
        return map.get(key);
    }

    public static <K, V> Map<K, V> filterByKey(Map<K, V> map, Predicate<K> condition) {
        return filter(map, entry -> condition.test(entry.getKey()));
    }

    public static <K, V> Map<K, V> filterByValue(Map<K, V> map, Predicate<V> condition) {
        return filter(map, entry -> condition.test(entry.getValue()));
    }

    public static <K, V> Map<K, V> filter(Map<K, V> map, Predicate<Entry<K, V>> condition) {
        return map.entrySet().stream().filter(condition).collect(toMap(Entry::getKey, Entry::getValue));
    }

    public static <K1, V1, K2, V2> Map<K2, V2> convert(Map<K1, V1> map, Function<K1, K2> kMap, Function<V1, V2> vMap) {
        return map.entrySet().stream().collect(toMap(
            entry -> kMap.apply(entry.getKey()),
            entry -> vMap.apply(entry.getValue())));
    }

    public static <K, V, C> Map<K, C> convert(Map<K, V> map, Function<V, C> valueMapper) {
        return map.entrySet().stream().collect(toMap(Entry::getKey, entry -> valueMapper.apply(entry.getValue())));
    }

    public static <K, V> void add(Map<K, Collection<V>> map, K key, V element) {
        Collection<V> values = getOrDefault(map, key, ArrayList::new);
        values.add(element);
        map.put(key, values);
    }

    public static <K, V> void addAll(Map<K, Collection<V>> map, K key, Collection<V> elements) {
        Collection<V> values = getOrDefault(map, key, ArrayList::new);
        values.addAll(elements);
        map.put(key, values);
    }

    public static <K, V> void remove(Map<K, Collection<V>> map, K key, V element) {
        Collection<V> values = getOrDefault(map, key, ArrayList::new);
        values.remove(element);
        map.put(key, values);
    }

    public static <K, V> void removeAll(Map<K, Collection<V>> map, K key, Collection<V> elements) {
        Collection<V> values = getOrDefault(map, key, ArrayList::new);
        values.removeAll(elements);
        map.put(key, values);
    }
}
