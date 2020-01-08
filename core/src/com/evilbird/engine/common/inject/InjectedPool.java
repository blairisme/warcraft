/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.inject;

import com.badlogic.gdx.utils.Pool;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Instances of this class are used to obtain objects, which are created using
 * injection and stored in a pool for reuse.
 *
 * @param <T> the type of element provided by the InjectedPool.
 *
 * @author Blair Butterworth
 */
public class InjectedPool<T> extends Pool<T>
{
    private Provider<T> provider;

    @Inject
    public InjectedPool(Provider<T> provider) {
        this.provider = provider;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected T newObject() {
        T result = provider.get();
        if (result instanceof PooledObject) {
            PooledObject<T> pooledObject = (PooledObject<T>)result;
            pooledObject.setPool(this);
        }
        return result;
    }
}