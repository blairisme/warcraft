package com.evilbird.engine.common.inject;

import com.evilbird.engine.common.lang.Identifier;

import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ProviderAdapter<T> implements IdentifiedAssetProvider<T>
{
    private Provider<T> delegate;

    public ProviderAdapter(Provider<T> delegate)
    {
        this.delegate = delegate;
    }

    @Override
    public void load()
    {
    }

    @Override
    public T get(Identifier identifier)
    {
        return delegate.get();
    }
}
