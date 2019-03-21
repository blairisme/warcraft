/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class IndexedSet<T extends Indexible<I>, I> extends LinkedHashSet<T>
{
    private transient Map<Object, T> index;

    public IndexedSet() {
        super();
    }

    public IndexedSet(Collection<T> collection) {
        super(collection);
    }

    @Override
    public boolean add(T entry) {
        boolean result = super.add(entry);
        buildIndex();
        index.put(entry.getIndex(), entry);
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean result = super.addAll(collection);
        buildIndex();
        collection.forEach(entry -> index.put(entry.getIndex(), entry));
        return result;
    }

    public boolean containsKey(I key) {
        buildIndex();
        return index.containsKey(key);
    }

    public T get(I key) {
        buildIndex();
        return index.get(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object entry) {
        boolean result = super.remove(entry);
        buildIndex();
        index.remove(((T)entry).getIndex());
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean removeAll(Collection<?> collection) {
        boolean result = super.removeAll(collection);
        buildIndex();
        collection.forEach(entry -> index.remove(((T)entry).getIndex()));
        return result;
    }

    public void removeKey(I key) {
        T entry = get(key);
        if (entry != null) {
            remove(entry);
        }
    }

    private void buildIndex() {
        if (index == null) {
            index = new HashMap<>();
            forEach(entry -> index.put(entry.getIndex(), entry));
        }
    }
}
