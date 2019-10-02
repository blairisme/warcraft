/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
            case Embark: return getAction(loadPool, identifier);
            case Disembark: return getAction(unloadPool, identifier);
            default: throw new UnsupportedOperationException();
        }
    }

    private <T extends Action> T getAction(InjectedPool<T> pool, ActionIdentifier identifier) {
        T result = pool.obtain();
        result.setIdentifier(identifier);
        return result;
    }
}