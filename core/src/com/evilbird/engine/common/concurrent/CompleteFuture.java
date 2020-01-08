/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.concurrent;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * A {@link Future} implementation representing an operation that has already
 * completed.
 *
 * @param <T>   the type of element returned by the operation whose execution
 *              the future represents.
 *
 * @author Blair Butterworth
 */
public class CompleteFuture<T> implements Future<T>
{
    private T result;

    public CompleteFuture() {
    }

    public CompleteFuture(T result) {
        this.result = result;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public T get() {
        return result;
    }

    @Override
    public T get(long timeout, TimeUnit unit) {
        return result;
    }
}
