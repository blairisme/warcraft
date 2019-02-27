/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.inject;

import com.badlogic.gdx.utils.Pool;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Instances of this class are used to obtain objects, which are created using
 * injection and stored in a pool for reuse.
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
    protected T newObject() {
        return provider.get();
    }
}