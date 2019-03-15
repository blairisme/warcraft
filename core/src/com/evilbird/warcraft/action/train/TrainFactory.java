/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

public class TrainFactory implements ActionProvider
{
    private TrainReporter reporter;
    private InjectedPool<TrainAction> trainPool;
    private InjectedPool<TrainCancel> cancelPool;

    @Inject
    public TrainFactory(
        TrainReporter reporter,
        InjectedPool<TrainAction> trainPool,
        InjectedPool<TrainCancel> cancelPool)
    {
        this.reporter = reporter;
        this.trainPool = trainPool;
        this.cancelPool = cancelPool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(TrainActions.class, action);
        TrainActions trainAction = (TrainActions)action;

        switch (trainAction) {
            case TrainFootman:
            case TrainPeasant: return getTrainAction(trainAction);
            case TrainFootmanCancel:
            case TrainPeasantCancel: return getTrainCancel(trainAction);
            default: throw new UnsupportedOperationException();
        }
    }

    private Action getTrainAction(TrainActions trainAction) {
        TrainAction action = trainPool.obtain();
        action.setObserver(reporter);
        action.setTrainType(trainAction.getUnitType());
        action.setTrainDuration(trainAction.getTrainTime());
        action.setTrainCost(trainAction.getResourceRequirements());
        return action;
    }

    private Action getTrainCancel(TrainActions trainAction) {
        TrainCancel action = cancelPool.obtain();
        action.setTrainCost(trainAction.getResourceRequirements());
        return action;
    }
}
