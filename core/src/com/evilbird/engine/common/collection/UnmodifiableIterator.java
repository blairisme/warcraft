/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.collection;

import java.util.Iterator;

/**
 * Decorates an iterator such that it cannot be modified. Attempts to modify it
 * will result in an {@link UnsupportedOperationException}.
 *
 * @param <T> the type of elements returned by this iterator.
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
