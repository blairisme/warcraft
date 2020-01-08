/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.collection;

import java.util.ListIterator;

/**
 * An {@link ListIterator} that has no elements.
 *
 * @param <T> the type of elements returned by this iterator.
 *
 * @author Blair Butterworth
 */
public class EmptyIterator<T> implements ListIterator<T> {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public T previous() {
        return null;
    }

    @Override
    public int nextIndex() {
        return 0;
    }

    @Override
    public int previousIndex() {
        return 0;
    }

    @Override
    public void remove() {
    }

    @Override
    public void set(T t) {
    }

    @Override
    public void add(T t) {
    }
}
