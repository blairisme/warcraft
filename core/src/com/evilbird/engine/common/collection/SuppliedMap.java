/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * A {@link Map} implementation whose content is supplied by a given
 * {@link Function}.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 *
 * @author Blair Butterworth
 */
public class SuppliedMap<K, V> implements Map<K, V>
{
    private Function<K, V> supplier;

    public SuppliedMap(Function<K, V> supplier) {
        this.supplier = supplier;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        return supplier.apply((K)key);
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }
}
