/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
