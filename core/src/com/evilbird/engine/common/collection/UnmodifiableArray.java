/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.collection;

import com.badlogic.gdx.utils.Array;

/**
 * Decorates an {@link Array} such that it cannot be modified. Attempts to
 * modify it will result in an {@link UnsupportedOperationException}.
 *
 * @param <T> the type of elements returned by this Array.
 *
 * @author Blair Butterworth
 */
public class UnmodifiableArray<T> extends Array<T>
{
    @Override
    public void add(T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addAll(T[] array, int start, int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(int index, T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insert(int index, T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void swap(int first, int second) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeValue(T value, boolean identity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T removeIndex(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeRange(int start, int end) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Array<? extends T> array, boolean identity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T pop() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}
