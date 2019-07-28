/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game;

import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.lang.Identifier;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link GameFactory} implementation fulfilling the composite pattern,
 * allowing multiple factories to be "combined" and made to appear as a single
 * factory.
 *
 * @param <V> the type of objects produced by the {@code GameFactorySet}.
 *
 * @author Blair Butterworth
 */
public class GameFactorySet<V> implements GameFactory<V>
{
    private Map<Object, GameFactory<? extends V>> providers;

    public GameFactorySet() {
        providers = new HashMap<>();
    }

    public void addProvider(Identifier key, GameFactory<? extends V>provider) {
        providers.put(key, provider);
    }

    public void addProvider(Class<?> key, GameFactory<? extends V> provider) {
        providers.put(key, provider);
    }

    public void addProvider(Identifier[] keys, GameFactory<? extends V> provider) {
        for (Identifier key: keys) {
            addProvider(key, provider);
        }
    }

    public void addProvider(GameFactorySet<? extends V> other) {
        this.providers.putAll(other.providers);
    }

    @Override
    public V get(Identifier key) {
        if (providers.containsKey(key)) {
            return providers.get(key).get(key);
        }
        if (providers.containsKey(key.getClass())) {
            return providers.get(key.getClass()).get(key);
        }
        throw new UnknownEntityException(key);
    }

    @Override
    public void load(GameContext context) {
        for (GameFactory<? extends V> delegate : providers.values()) {
            delegate.load(context);
        }
    }

    @Override
    public void unload(GameContext context) {
        for (GameFactory<? extends V> delegate : providers.values()) {
            delegate.unload(context);
        }
    }
}
