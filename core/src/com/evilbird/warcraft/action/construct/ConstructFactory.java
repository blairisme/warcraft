/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.utilities.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

public class ConstructFactory implements ActionProvider
{
    private ConstructReporter reporter;
    private InjectedPool<ConstructAction> constructPool;
    private InjectedPool<ConstructCancel> cancelPool;

    @Inject
    public ConstructFactory(
        ConstructReporter reporter,
        InjectedPool<ConstructAction> constructPool,
        InjectedPool<ConstructCancel> cancelPool)
    {
        this.reporter = reporter;
        this.constructPool = constructPool;
        this.cancelPool = cancelPool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(ConstructActions.class, action);
        ConstructActions constructAction = (ConstructActions)action;

        switch (constructAction) {
            case ConstructBarracksCancel:
            case ConstructTownhallCancel:
            case ConstructFarmCancel: return getCancelAction(constructAction);
            case ConstructBarracks:
            case ConstructTownHall:
            case ConstructFarm: return getConstructAction(constructAction);
            default: throw new UnsupportedOperationException();
        }
    }

    private Action getConstructAction(ConstructActions action) {
        ConstructAction constructAction = constructPool.obtain();
        constructAction.setObserver(reporter);
        constructAction.setBuildType(action.getBuildType());
        constructAction.setBuildDuration(action.getBuildDuration());
        constructAction.setBuildCost(action.getResourceRequirements());
        return constructAction;
    }

    private Action getCancelAction(ConstructActions action) {
        ConstructCancel constructCancel = cancelPool.obtain();
        constructCancel.setBuildCost(action.getResourceRequirements());
        return constructCancel;
    }
}
