/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import java.util.Iterator;
import java.lang.UnsupportedOperationException;

/**
 * Decorates an iterator such that it cannot be modified. Attempts to modify it
 * will result in an {@link UnsupportedOperationException}.
 *
 * @author Blair Butterworth
 */
public class UnmodifiableIterator<T> implements Iterator<T>
{
    private final Iterator<? extends T> iterator;

    public UnmodifiableIterator(final Iterator<? extends T> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        return iterator.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}
