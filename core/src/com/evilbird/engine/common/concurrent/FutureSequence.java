/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.concurrent;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * A {@link Future} implementation that executes a series of asynchronous
 * operations in sequence.
 *
 * @author Blair Butterworth
 */
public class FutureSequence implements Future<Void>
{
    private Future<?> current;
    private List<Supplier<Future<?>>> sequence;
    private Iterator<Supplier<Future<?>>> iterator;

    public FutureSequence(List<Supplier<Future<?>>> sequence) {
        this.sequence = sequence;
    }

    public void start() {
        this.iterator = sequence.iterator();
        this.current = iterator.next().get();
    }

    @Override
    public boolean isDone() {
        boolean complete = current.isDone();
        if (complete && iterator.hasNext()) {
            current = iterator.next().get();
            complete = false;
        }
        return complete;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Void get(){
        return null;
    }

    @Override
    public Void get(long timeout, TimeUnit unit){
        return null;
    }
}