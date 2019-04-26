/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.action.framework.ScenarioAction;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.common.create.CreateAction.create;
import static com.evilbird.warcraft.action.common.resource.ResourceTransferAction.purchase;
import static com.evilbird.warcraft.action.move.MoveAdjacent.moveAdjacentSubject;
import static com.evilbird.warcraft.action.train.TrainAction.startProducing;
import static com.evilbird.warcraft.action.train.TrainEvents.onTrainCompleted;
import static com.evilbird.warcraft.action.train.TrainEvents.onTrainStarted;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;

/**
 * Instances of this action sequence create a new unit, decrementing the
 * players resources and adding delay before the new unit can be used. The new
 * unit will be placed next to the building that created it, in the first
 * unoccupied space.
 *
 * @author Blair Butterworth
 */
public class TrainSequence extends ScenarioAction<TrainActions>
{
    private transient TrainReporter reporter;

    /**
     * Constructs a new instance of this class given a {@link TrainReporter}
     * used to report events when training begins and completes, as well as
     * for the transfer of funds involved in training.
     *
     * @param reporter  a {@code TrainReporter} instance. This parameter
     *                  cannot be {@code null}.
     */
    @Inject
    public TrainSequence(TrainReporter reporter) {
        this.reporter = reporter;
    }

    @Override
    protected void steps(TrainActions type) {
        scenario(type);
        given(isAlive());
        then(purchase(type, reporter), onTrainStarted(reporter));
        then(startProducing(type));
        thenUpdate(create(type.getItemType(), reporter));
        then(moveAdjacentSubject(), onTrainCompleted(reporter));
    }
}