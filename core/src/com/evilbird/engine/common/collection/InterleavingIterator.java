/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.collection;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;

/**
 * A {@link Iterator} implementation that facilitates iterating over multiple
 * iterators, one by one.
 *
 * @author Blair Butterworth
 */
public class InterleavingIterator<T> implements Iterator<T>
{
    private Queue<Iterator<T>> iteratorQueue;
    private T next;

    public InterleavingIterator(Collection<Collection<T>> values) {
        Objects.requireNonNull(values);
        iteratorQueue = new ArrayDeque<>();
        for (Collection<T> collection: values) {
            iteratorQueue.add(collection.iterator());
        }
    }

    @Override
    public boolean hasNext() {
        return getNext() != null;
    }

    @Override
    public T next() {
        T n = getNext();
        next = null;
        if (n == null) throw new NoSuchElementException();
        return n;
    }

    private T getNext() {
        while (next == null && !iteratorQueue.isEmpty()) {
            Iterator<T> iterator = iteratorQueue.poll();
            if (iterator.hasNext()) {
                next = iterator.next();
                if (iterator.hasNext()) {
                    iteratorQueue.add(iterator);
                }
                return next;
            }
        }
        return next;
    }
}