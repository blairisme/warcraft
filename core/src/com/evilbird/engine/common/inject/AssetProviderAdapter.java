package com.evilbird.engine.common.inject;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class AssetProviderAdapter<T> implements IdentifiedAssetProvider<T>
{
    private AssetProvider<T> delegate;

    public AssetProviderAdapter(AssetProvider<T> delegate)
    {
        this.delegate = delegate;
    }

    @Override
    public void load()
    {
        delegate.load();
    }

    @Override
    public T get(Identifier identifier)
    {
        return delegate.get();
    }
}
