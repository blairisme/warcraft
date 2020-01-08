/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.confirm;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Action}s that display a confirm
 * effect.
 *
 * @author Blair Butterworth
 */
public class ConfirmFactory implements ActionProvider
{
    private InjectedPool<ConfirmItem> itemPool;
    private InjectedPool<ConfirmLocation> locationPool;
    private InjectedPool<ConfirmAttack> attackPool;

    @Inject
    public ConfirmFactory(
        InjectedPool<ConfirmItem> itemPool,
        InjectedPool<ConfirmLocation> locationPool,
        InjectedPool<ConfirmAttack> attackPool)
    {
        this.itemPool = itemPool;
        this.locationPool = locationPool;
        this.attackPool = attackPool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        switch ((ConfirmActions)action) {
            case ConfirmLocation: return locationPool.obtain();
            case ConfirmTarget: return itemPool.obtain();
            case ConfirmAttack: return attackPool.obtain();
            default: throw new UnsupportedOperationException();
        }
    }
}
