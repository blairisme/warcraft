/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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
    private InjectedPool<GatherWood> woodPool;
    private InjectedPool<GatherCancel> cancelPool;

    @Inject
    public GatherFactory(
        InjectedPool<GatherGold> goldPool,
        InjectedPool<GatherWood> woodPool,
        InjectedPool<GatherCancel> cancelPool)
    {
        this.goldPool = goldPool;
        this.woodPool = woodPool;
        this.cancelPool = cancelPool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(GatherActions.class, action);
        GatherActions gatherAction = (GatherActions)action;

        switch (gatherAction) {
            case GatherGold: return goldPool.obtain();
            case GatherWood: return woodPool.obtain();
            case GatherCancel: return cancelPool.obtain();
            default: throw new UnsupportedOperationException();
        }
    }
}
