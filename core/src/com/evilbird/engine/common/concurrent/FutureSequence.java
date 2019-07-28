/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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