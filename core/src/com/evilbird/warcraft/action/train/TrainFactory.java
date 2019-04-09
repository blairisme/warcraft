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

/**
 * Instances of this factory create {@link TrainSequence} and {@link TrainCancel}
 * actions.
 *
 * @author Blair Butterworth
 */
public class TrainFactory implements ActionProvider
{
    private InjectedPool<TrainSequence> trainPool;
    private InjectedPool<TrainCancel> cancelPool;

    @Inject
    public TrainFactory(
        InjectedPool<TrainSequence> trainPool,
        InjectedPool<TrainCancel> cancelPool)
    {
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
        TrainSequence action = trainPool.obtain();
        action.setIdentifier(trainAction);
        return action;
    }

    private Action getTrainCancel(TrainActions trainAction) {
        TrainCancel action = cancelPool.obtain();
        action.setIdentifier(trainAction);
        //action.setTrainCost(trainAction.getValues());
        return action;
    }
}
