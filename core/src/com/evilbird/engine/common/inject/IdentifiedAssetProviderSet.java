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
import java.util.HashMap;
import java.util.Map;

public class IdentifiedAssetProviderSet<T> implements IdentifiedAssetProvider<T>
{
    private Map<Identifier, IdentifiedAssetProvider<? extends T>> delegates;

    public IdentifiedAssetProviderSet() {
        delegates = new HashMap<>();
    }

    public void addProvider(Identifier key, Provider<? extends T> provider) {
        delegates.put(key, new ProviderAdapter<>(provider));
    }

    public void addProvider(Identifier key, AssetProvider<? extends T> provider) {
        delegates.put(key, new AssetProviderAdapter<>(provider));
    }

    public void addProvider(Identifier key, IdentifiedAssetProvider<? extends T> provider) {
        delegates.put(key, provider);
    }

    public void addProvider(IdentifiedAssetProviderSet<T> provider) {
        for (Identifier key : provider.delegates.keySet()) {
            addProvider(key, provider);
        }
    }

    public void load() {
        for (IdentifiedAssetProvider<? extends T> delegate : delegates.values()) {
            delegate.load();
        }
    }

    public T get(Identifier key) {
        IdentifiedAssetProvider<? extends T> delegate = delegates.get(key);

        if (delegate != null) {
            return delegate.get(key);
        }
        return null;
    }
}