/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
