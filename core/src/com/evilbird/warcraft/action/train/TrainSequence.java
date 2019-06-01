/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.warcraft.action.common.scenario.ScenarioAction;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.ActionRecipient.Target;
import static com.evilbird.engine.action.common.AudibleAction.play;
import static com.evilbird.warcraft.action.common.create.CreateAction.create;
import static com.evilbird.warcraft.action.common.transfer.TransferAction.purchase;
import static com.evilbird.warcraft.action.move.MoveAdjacent.moveAdjacentSubject;
import static com.evilbird.warcraft.action.train.TrainAction.startProducing;
import static com.evilbird.warcraft.action.train.TrainEvents.onTrainCompleted;
import static com.evilbird.warcraft.action.train.TrainEvents.onTrainStarted;
import static com.evilbird.warcraft.action.train.TrainTimes.trainTime;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.unit.UnitCosts.costOf;
import static com.evilbird.warcraft.item.unit.UnitSound.Ready;

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
    protected void steps(TrainActions action) {
        scenario(action);
        steps(action.getUnitType());
    }

    protected void steps(UnitType unit) {
        given(isAlive());
        then(purchase(costOf(unit), reporter), onTrainStarted(reporter));
        then(startProducing(trainProgress(unit), trainTime(unit)));
        thenUpdate(create(unit, reporter));
        then(moveAdjacentSubject(), onTrainCompleted(reporter), play(Target, Ready));
    }

    private float trainProgress(UnitType unit) {
        Building building = (Building)getItem();
        if (building.isProducing()) {
            return building.getProductionProgress() * trainTime(unit);
        }
        return 0;
    }
}