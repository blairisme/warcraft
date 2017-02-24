package com.evilbird.engine.utility;

import java.util.HashMap;
import java.util.Map;

public class AssetObjectProviderSet<T>
{
    private Map<Identifier, AssetObjectProvider<T>> delegates;

    public AssetObjectProviderSet()
    {
        delegates = new HashMap<Identifier, AssetObjectProvider<T>>();
    }

    public void addProvider(Identifier identifier, AssetObjectProvider<T> provider)
    {
        delegates.put(identifier, provider);
    }

    public void load()
    {
        for (AssetObjectProvider<T> delegate: delegates.values()){
            delegate.load();
        }
    }

    public boolean contains(Identifier id)
    {
        return delegates.containsKey(id);
    }

    public T get(Identifier id)
    {
        AssetObjectProvider<T> delegate = delegates.get(id);
        return delegate.get();
    }
}
