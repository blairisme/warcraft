/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.game;

import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.lang.Identifier;

import java.util.EnumSet;
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

    public void addProvider(EnumSet<? extends Identifier> keys, GameFactory<? extends V> provider) {
        addProvider(keys.toArray(new Identifier[0]), provider);
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
