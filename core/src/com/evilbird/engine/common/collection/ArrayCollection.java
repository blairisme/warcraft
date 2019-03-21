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

import java.util.Collection;
import java.util.Iterator;

public class ArrayCollection<T> implements Collection<T>
{
    private Array<T> array;

    public ArrayCollection() {
        this(new Array<>());
    }

    public ArrayCollection(Array<T> array) {
        this.array = array;
    }

    @Override
    public int size() {
        return array.size;
    }

    @Override
    public boolean isEmpty() {
        return array.size == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object object) {
        return array.contains((T)object, false);
    }

    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }

    @Override
    public Object[] toArray() {
        return array.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return array.toArray(a.getClass().getComponentType());
    }

    @Override
    public boolean add(T value) {
        array.add(value);
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object value) {
        return array.removeValue((T)value, false);
    }

    public T removeByIndex(int index) {
        return array.removeIndex(index);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        array.clear();
    }
}
