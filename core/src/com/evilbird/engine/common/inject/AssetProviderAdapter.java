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
