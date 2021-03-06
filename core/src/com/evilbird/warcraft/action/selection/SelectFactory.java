/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selection;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create actions that alter item selection.
 *
 * @author Blair Butterworth
 */
public class SelectFactory implements ActionProvider
{
    private InjectedPool<DeselectAction> deselectPool;
    private InjectedPool<SelectInvert> invertPool;
    private InjectedPool<SelectFlash> flashPool;

    @Inject
    public SelectFactory(
        InjectedPool<DeselectAction> deselectPool,
        InjectedPool<SelectInvert> invertPool,
        InjectedPool<SelectFlash> flashPool)
    {
        this.deselectPool = deselectPool;
        this.invertPool = invertPool;
        this.flashPool = flashPool;
    }

    @Override
    public Action get(ActionIdentifier identifier) {
        Validate.isInstanceOf(SelectActions.class, identifier);
        switch ((SelectActions)identifier) {
            case SelectDeselect: return getAction(deselectPool, identifier);
            case SelectInvert: return getAction(invertPool, identifier);
            case SelectFlash: return getAction(flashPool, identifier);
            default: throw new UnsupportedOperationException();
        }
    }

    private <T extends Action> T getAction(InjectedPool<T> pool, ActionIdentifier identifier) {
        T result = pool.obtain();
        result.setIdentifier(identifier);
        return result;
    }
}