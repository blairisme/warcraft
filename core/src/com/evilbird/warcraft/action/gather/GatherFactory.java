/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.utilities.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

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
    public Action get(ActionIdentifier action, ActionContext context) {
        Validate.isInstanceOf(GatherActions.class, action);
        GatherActions gatherAction = (GatherActions)action;

        switch (gatherAction) {
            case GatherGold: return getGatherGoldAction();
            case GatherWood: return getGatherWoodAction();
            case GatherCancel: return getGatherCancelAction();
            default: throw new UnsupportedOperationException();
        }
    }

    private Action getGatherGoldAction() {
        Action result = goldPool.obtain();
        return result;
    }

    private Action getGatherWoodAction() {
        Action result = woodPool.obtain();
        return result;
    }

    private Action getGatherCancelAction() {
        Action result = cancelPool.obtain();
        return result;
    }
}
