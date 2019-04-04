/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

public class ConstructFactory implements ActionProvider
{
    private InjectedPool<ConstructSequence> constructPool;
    private InjectedPool<ConstructCancel> cancelPool;

    @Inject
    public ConstructFactory(
        InjectedPool<ConstructSequence> constructPool,
        InjectedPool<ConstructCancel> cancelPool)
    {
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
        ConstructSequence constructAction = constructPool.obtain();
        constructAction.setIdentifier(action);
        return constructAction;
    }

    private Action getCancelAction(ConstructActions action) {
        ConstructCancel constructCancel = cancelPool.obtain();
        constructCancel.setIdentifier(action);
        return constructCancel;
    }
}
