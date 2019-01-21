/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.inject;

import com.evilbird.engine.common.lang.Identifier;

import javax.inject.Provider;

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
