/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
