/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;

import java.util.HashMap;
import java.util.Map;

/**
 * A factory that provides spell actions.
 *
 * @author Blair Butterworth
 */
public class SpellProvider
{
    private Map<ActionIdentifier, InjectedPool<? extends Action>> pools;

    public SpellProvider() {
        pools = new HashMap<>();
    }

    public void addActionPool(ActionIdentifier identifier, InjectedPool<? extends Action> pool) {
        pools.put(identifier, pool);
    }

    public void addActionProvider(SpellProvider provider) {
        pools.putAll(provider.pools);
    }

    public Action getAction(ActionIdentifier identifier) {
        InjectedPool<? extends Action> pool = pools.get(identifier);
        Action result = pool.obtain();
        result.setIdentifier(identifier);
        return result;
    }
}
