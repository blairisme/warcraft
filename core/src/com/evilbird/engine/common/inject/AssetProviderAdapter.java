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

/**
 * Instances of this adapter wrap an {@link AssetProvider}, decorating it as an
 * {@link IdentifiedAssetProvider}.
 *
 * @param <T> the type of elements returned by the AssetProviderAdapter.
 *
 * @author Blair Butterworth
 */
public class AssetProviderAdapter<T> implements IdentifiedAssetProvider<T>
{
    private AssetProvider<T> delegate;

    public AssetProviderAdapter(AssetProvider<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void load() {
        delegate.load();
    }

    @Override
    public T get(Identifier identifier) {
        return delegate.get();
    }
}
