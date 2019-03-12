/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.inject;

import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.lang.Identifier;

import javax.inject.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class represent a collection of asset factories that
 * produce objects identified by given value.
 *
 * @param <T> the type of the objects produced by this factory.
 *
 * @author Blair Butterworth
 */
public class IdentifiedAssetProviderSet<T> implements IdentifiedAssetProvider<T>
{
    private Map<Object, IdentifiedAssetProvider<? extends T>> providers;

    public IdentifiedAssetProviderSet() {
        providers = new HashMap<>();
    }

    public void addProvider(Identifier key, Provider<? extends T> provider) {
        providers.put(key, new ProviderAdapter<>(provider));
    }

    public void addProvider(Identifier key, AssetProvider<? extends T> provider) {
        providers.put(key, new AssetProviderAdapter<>(provider));
    }

    public void addProvider(Identifier key, IdentifiedAssetProvider<? extends T> provider) {
        providers.put(key, provider);
    }

    public void addProvider(Class<?> key, IdentifiedAssetProvider<? extends T> provider) {
        providers.put(key, provider);
    }

    public void addProvider(Identifier[] keys, IdentifiedAssetProvider<? extends T> provider) {
        for (Identifier key: keys) {
            addProvider(key, provider);
        }
    }

    public void addProvider(IdentifiedAssetProviderSet<T> other) {
        this.providers.putAll(other.providers);
    }

    @Override
    public void load() {
        for (IdentifiedAssetProvider<? extends T> delegate : providers.values()) {
            delegate.load();
        }
    }

    @Override
    public T get(Identifier key) {
        if (providers.containsKey(key)) {
            return providers.get(key).get(key);
        }
        if (providers.containsKey(key.getClass())) {
            return providers.get(key.getClass()).get(key);
        }
        throw new UnknownEntityException(key);
    }
}