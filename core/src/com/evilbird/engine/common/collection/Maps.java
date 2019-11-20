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
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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
    public static <K, V> V getOrDefaultSupplied(Map<K, V> map, K key, Supplier<V> defaultSupplier) {
        if (!map.containsKey(key)) {
            return defaultSupplier.get();
        }
        return map.get(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or
     * {@code defaultValue} if this map contains no mapping for the key.
     *
     * @implSpec
     * The default implementation makes no guarantees about synchronization
     * or atomicity properties of this method. Any implementation providing
     * atomicity guarantees must override this method and document its
     * concurrency properties.
     *
     * @param key the key whose associated value is to be returned
     * @param defaultValue the default mapping of the key
     * @return the value to which the specified key is mapped, or
     * {@code defaultValue} if this map contains no mapping for the key
     * @throws ClassCastException if the key is of an inappropriate type for
     * this map
     * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     * does not permit null keys
     * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    public static <K, V> V getOrDefault(Map<K, V> map, Object key, V defaultValue) {
        V v;
        return (((v = map.get(key)) != null) || map.containsKey(key))
                ? v
                : defaultValue;
    }

    public static <K, V> Map<K, V> filter(Map<K, V> map, Predicate<Entry<K, V>> condition) {
        Map<K, V> filtered = new HashMap<>(map.size());
        for (Entry<K, V> entry: map.entrySet()) {
            if (condition.test(entry)) {
                filtered.put(entry.getKey(), entry.getValue());
            }
        }
        return filtered;
    }

    public static <K1, V1, K2, V2> Map<K2, V2> convert(Map<K1, V1> map, Function<K1, K2> kMap, Function<V1, V2> vMap) {
        Map<K2, V2> converted = new HashMap<>(map.size());
        for (Entry<K1, V1> entry: map.entrySet()) {
            converted.put(kMap.apply(entry.getKey()), vMap.apply(entry.getValue()));
        }
        return converted;
    }

    public static <K, V, C> Map<K, C> convert(Map<K, V> map, Function<V, C> valueMapper) {
        Map<K, C> converted = new HashMap<>(map.size());
        for (Entry<K, V> entry: map.entrySet()) {
            converted.put(entry.getKey(), valueMapper.apply(entry.getValue()));
        }
        return converted;
    }

    public static <K, V> void add(Map<K, Collection<V>> map, K key, V element) {
        Collection<V> values = getOrDefaultSupplied(map, key, ArrayList::new);
        values.add(element);
        map.put(key, values);
    }

    public static <K, V> void addAll(Map<K, Collection<V>> map, K key, Collection<V> elements) {
        Collection<V> values = getOrDefaultSupplied(map, key, ArrayList::new);
        values.addAll(elements);
        map.put(key, values);
    }

    public static <K, V> void remove(Map<K, Collection<V>> map, K key, V element) {
        Collection<V> values = getOrDefaultSupplied(map, key, ArrayList::new);
        values.remove(element);
        map.put(key, values);
    }

    public static <K, V> void removeAll(Map<K, Collection<V>> map, K key, Collection<V> elements) {
        Collection<V> values = getOrDefaultSupplied(map, key, ArrayList::new);
        values.removeAll(elements);
        map.put(key, values);
    }

    /**
     * Performs the given action for each entry in this map until all entries
     * have been processed or the action throws an exception.   Unless
     * otherwise specified by the implementing class, actions are performed in
     * the order of entry set iteration (if an iteration order is specified.)
     * Exceptions thrown by the action are relayed to the caller.
     *
     * @implSpec
     * The default implementation is equivalent to, for this {@code map}:
     * <pre> {@code
     * for (Map.Entry<K, V> entry : map.entrySet())
     *     action.accept(entry.getKey(), entry.getValue());
     * }</pre>
     *
     * The default implementation makes no guarantees about synchronization
     * or atomicity properties of this method. Any implementation providing
     * atomicity guarantees must override this method and document its
     * concurrency properties.
     *
     * @param action The action to be performed for each entry
     * @throws NullPointerException if the specified action is null
     * @throws ConcurrentModificationException if an entry is found to be
     * removed during iteration
     */
    public static <K, V> void forEach(Map<K, V> map, BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch(IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
            action.accept(k, v);
        }
    }

    /**
     * If the specified key is not already associated with a value (or is mapped
     * to {@code null}), attempts to compute its value using the given mapping
     * function and enters it into this map unless {@code null}.
     *
     * <p>If the function returns {@code null} no mapping is recorded. If
     * the function itself throws an (unchecked) exception, the
     * exception is rethrown, and no mapping is recorded.  The most
     * common usage is to construct a new object serving as an initial
     * mapped value or memoized result, as in:
     *
     * <pre> {@code
     * map.computeIfAbsent(key, k -> new Value(f(k)));
     * }</pre>
     *
     * <p>Or to implement a multi-value map, {@code Map<K,Collection<V>>},
     * supporting multiple values per key:
     *
     * <pre> {@code
     * map.computeIfAbsent(key, k -> new HashSet<V>()).add(v);
     * }</pre>
     *
     *
     * @implSpec
     * The default implementation is equivalent to the following steps for this
     * {@code map}, then returning the current value or {@code null} if now
     * absent:
     *
     * <pre> {@code
     * if (map.get(key) == null) {
     *     V newValue = mappingFunction.apply(key);
     *     if (newValue != null)
     *         map.put(key, newValue);
     * }
     * }</pre>
     *
     * <p>The default implementation makes no guarantees about synchronization
     * or atomicity properties of this method. Any implementation providing
     * atomicity guarantees must override this method and document its
     * concurrency properties. In particular, all implementations of
     * subinterface {@link java.util.concurrent.ConcurrentMap} must document
     * whether the function is applied once atomically only if the value is not
     * present.
     *
     * @param key key with which the specified value is to be associated
     * @param mappingFunction the function to compute a value
     * @return the current (existing or computed) value associated with
     *         the specified key, or null if the computed value is null
     * @throws NullPointerException if the specified key is null and
     *         this map does not support null keys, or the mappingFunction
     *         is null
     * @throws UnsupportedOperationException if the {@code put} operation
     *         is not supported by this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws ClassCastException if the class of the specified key or value
     *         prevents it from being stored in this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    public static <K, V> V computeIfAbsent(Map<K, V> map, K key, Function<? super K, ? extends V> mappingFunction) {
        Objects.requireNonNull(mappingFunction);
        V v;
        if ((v = map.get(key)) == null) {
            V newValue;
            if ((newValue = mappingFunction.apply(key)) != null) {
                map.put(key, newValue);
                return newValue;
            }
        }
        return v;
    }
}
