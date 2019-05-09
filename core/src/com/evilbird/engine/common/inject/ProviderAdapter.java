/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.inject;

import com.evilbird.engine.common.lang.Identifier;

import javax.inject.Provider;

/**
 * Instances of this adapter wrap a {@link Provider}, decorating it as an
 * {@link IdentifiedAssetProvider}.
 *
 * @param <T> the type of elements returned by the ProviderAdapter.
 *
 * @author Blair Butterworth
 */
public class ProviderAdapter<T> implements IdentifiedAssetProvider<T>
{
    private Provider<T> delegate;

    public ProviderAdapter(Provider<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void load() {
    }

    @Override
    public T get(Identifier identifier) {
        return delegate.get();
    }
}
