/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.utilities.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

public class GatherFactory implements ActionProvider
{
    private GatherReporter reporter;
    private InjectedPool<GatherGold> goldPool;
    private InjectedPool<GatherWood> woodPool;
    private InjectedPool<GatherCancel> cancelPool;

    @Inject
    public GatherFactory(
        GatherReporter reporter,
        InjectedPool<GatherGold> goldPool,
        InjectedPool<GatherWood> woodPool,
        InjectedPool<GatherCancel> cancelPool)
    {
        this.reporter = reporter;
        this.goldPool = goldPool;
        this.woodPool = woodPool;
        this.cancelPool = cancelPool;
    }

    @Override
    public Action get(ActionIdentifier action) {
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
        GatherGold result = goldPool.obtain();
        result.setObserver(reporter);
        return result;
    }

    private Action getGatherWoodAction() {
        GatherWood result = woodPool.obtain();
        result.setObserver(reporter);
        return result;
    }

    private Action getGatherCancelAction() {
        return cancelPool.obtain();
    }
}
