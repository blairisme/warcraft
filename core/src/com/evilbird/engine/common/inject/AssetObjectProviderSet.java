package com.evilbird.engine.common.inject;

import com.evilbird.engine.common.lang.IdentifierNew;

import java.util.HashMap;
import java.util.Map;

public class AssetObjectProviderSet<T>
{
    private Map<IdentifierNew, AssetObjectProvider<T>> delegates;

    public AssetObjectProviderSet()
    {
        delegates = new HashMap<IdentifierNew, AssetObjectProvider<T>>();
    }

    public void addProvider(IdentifierNew identifier, AssetObjectProvider<T> provider)
    {
        delegates.put(identifier, provider);
    }

    public void load()
    {
        for (AssetObjectProvider<T> delegate: delegates.values()){
            delegate.load();
        }
    }

    public boolean contains(IdentifierNew id)
    {
        return delegates.containsKey(id);
    }

    public T get(IdentifierNew id)
    {
        AssetObjectProvider<T> delegate = delegates.get(id);
        return delegate.get();
    }
}
