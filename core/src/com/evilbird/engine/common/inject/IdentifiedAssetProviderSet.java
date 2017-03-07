package com.evilbird.engine.common.inject;

import com.evilbird.engine.common.lang.IdentifierNew;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class IdentifiedAssetProviderSet<T> implements IdentifiedAssetProvider<T>
{
    private Map<IdentifierNew, IdentifiedAssetProvider<? extends T>> delegates;

    public IdentifiedAssetProviderSet()
    {
        delegates = new HashMap<IdentifierNew, IdentifiedAssetProvider<? extends T>>();
    }

    public void addProvider(IdentifierNew key, Provider<? extends T> provider)
    {
        delegates.put(key, new ProviderAdapter(provider));
    }

    public void addProvider(IdentifierNew key, AssetProvider<? extends T> provider)
    {
        delegates.put(key, new AssetProviderAdapter(provider));
    }

    public void addProvider(IdentifierNew key, IdentifiedAssetProvider<? extends T> provider)
    {
        delegates.put(key, provider);
    }

    public void addProvider(IdentifiedAssetProviderSet<T> provider)
    {
        for (IdentifierNew key: provider.delegates.keySet())
        {
            addProvider(key, provider);
        }
    }

    public void load()
    {
        for (IdentifiedAssetProvider<? extends T> delegate: delegates.values())
        {
            delegate.load();
        }
    }

    public T get(IdentifierNew key)
    {
        IdentifiedAssetProvider<? extends T> delegate = delegates.get(key);

        if (delegate != null)
        {
            return delegate.get(key);
        }
        return null;
    }
}

/*
public class IdentifiedAssetProviderSet<K extends IdentifierNew, V> implements IdentifiedAssetProvider<K, V>
{
    private Map<K, IdentifiedAssetProvider<K, V>> delegates;

    public IdentifiedAssetProviderSet()
    {
        delegates = new HashMap<K, IdentifiedAssetProvider<K, V>>();
    }

    public void addProvider(K key, AssetObjectProvider<V> provider)
    {
        delegates.put(key, new IdentifiedAssetProviderAdapter(provider));
    }

    public void addProvider(K key, IdentifiedAssetProvider<K, V> provider)
    {
        delegates.put(key, provider);
    }

    public void addProvider(IdentifiedAssetProviderSet<K, V> provider)
    {
        for (K key: provider.delegates.keySet())
        {
            addProvider(key, provider);
        }
    }

    public void load()
    {
        for (IdentifiedAssetProvider<K, V> delegate: delegates.values())
        {
            delegate.load();
        }
    }

    public V get(K key)
    {
        IdentifiedAssetProvider<K, V> delegate = delegates.get(key);

        if (delegate != null)
        {
            return delegate.get(key);
        }
        return null;
    }
}
 */