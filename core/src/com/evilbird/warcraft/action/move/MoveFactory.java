/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.ActionProvider;

import javax.inject.Inject;

/**
 * Instances of this class create {@link Action Actions} that instruct a given
 * {@link GameObject} to move to a given target following a path that conforms to
 * its movement capability.
 *
 * @author Blair Butterworth
 */
public class MoveFactory implements ActionProvider
{
    private InjectedPool<MoveCancel> cancelPool;
    private InjectedPool<MoveToItemAction> moveItemPool;
    private InjectedPool<MoveToVectorAction> moveLocationPool;
    private InjectedPool<MoveWithinRangeAction> moveRangePool;

    @Inject
    public MoveFactory(
        InjectedPool<MoveCancel> cancelPool,
        InjectedPool<MoveToItemAction> moveItemPool,
        InjectedPool<MoveToVectorAction> moveLocationPool,
        InjectedPool<MoveWithinRangeAction> moveRangePool)
    {
        this.cancelPool = cancelPool;
        this.moveItemPool = moveItemPool;
        this.moveLocationPool = moveLocationPool;
        this.moveRangePool = moveRangePool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        switch((MoveActions)action) {
            case MoveToLocation: return moveLocationPool.obtain();
            case MoveToItem: return moveItemPool.obtain();
            case MoveWithRange: return moveRangePool.obtain();
            case MoveCancel: return cancelPool.obtain();
            default: throw new UnsupportedOperationException();
        }
    }
}
