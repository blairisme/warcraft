/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.assets;

import com.badlogic.gdx.assets.AssetManager;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * A {@link Future} implementation that returns when an {@link AssetManager}
 * has completed loading those assets it manages.
 *
 * @author Blair Butterworth
 */
public class AssetManagerFuture implements Future<Void>
{
    private AssetManager manager;

    public AssetManagerFuture(AssetManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean isDone() {
        return manager.update(10);
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
    public Void get() {
        return null;
    }

    @Override
    public Void get(long timeout, TimeUnit unit) {
        return null;
    }
}
