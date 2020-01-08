/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create actions related to gathering resources.
 *
 * @author Blair Butterworth
 */
public class GatherFactory implements ActionProvider
{
    private InjectedPool<GatherGold> goldPool;
    private InjectedPool<GatherOil> oilPool;
    private InjectedPool<GatherWood> woodPool;
    private InjectedPool<GatherCancel> cancelPool;

    @Inject
    public GatherFactory(
        InjectedPool<GatherGold> goldPool,
        InjectedPool<GatherOil> oilPool,
        InjectedPool<GatherWood> woodPool,
        InjectedPool<GatherCancel> cancelPool)
    {
        this.goldPool = goldPool;
        this.oilPool = oilPool;
        this.woodPool = woodPool;
        this.cancelPool = cancelPool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(GatherActions.class, action);
        GatherActions gatherAction = (GatherActions)action;

        switch (gatherAction) {
            case GatherGold: return goldPool.obtain();
            case GatherOil: return oilPool.obtain();
            case GatherWood: return woodPool.obtain();
            case GatherCancel: return cancelPool.obtain();
            default: throw new UnsupportedOperationException();
        }
    }
}
