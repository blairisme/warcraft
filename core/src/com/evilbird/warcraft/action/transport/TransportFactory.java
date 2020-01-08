/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.transport;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create actions that facilitate transportation of
 * units in transports.
 *
 * @author Blair Butterworth
 */
public class TransportFactory implements ActionProvider
{
    private InjectedPool<TransportLoad> loadPool;
    private InjectedPool<TransportUnload> unloadPool;

    @Inject
    public TransportFactory(
        InjectedPool<TransportLoad> loadPool,
        InjectedPool<TransportUnload> unloadPool)
    {
        this.loadPool = loadPool;
        this.unloadPool = unloadPool;
    }

    @Override
    public Action get(ActionIdentifier identifier) {
        Validate.isInstanceOf(TransportActions.class, identifier);
        switch ((TransportActions)identifier) {
            case TransportEmbark: return getAction(loadPool, identifier);
            case TransportDisembark: return getAction(unloadPool, identifier);
            default: throw new UnsupportedOperationException();
        }
    }

    private <T extends Action> T getAction(InjectedPool<T> pool, ActionIdentifier identifier) {
        T result = pool.obtain();
        result.setIdentifier(identifier);
        return result;
    }
}