/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
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
import static com.evilbird.warcraft.item.unit.UnitAttributes.costOf;
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
    private transient Events events;

    /**
     * Constructs a new instance of this class given an {@link EventQueue}
     * used to report events when training begins and completes, as well as
     * for the transferAll of funds involved in training.
     *
     * @param events  a {@code EventQueue} instance. This parameter
     *                  cannot be {@code null}.
     */
    @Inject
    public TrainSequence(EventQueue events) {
        this.events = events;
    }

    @Override
    protected void steps(TrainActions action) {
        scenario(action);
        steps(action.getUnitType());
    }

    protected void steps(UnitType unit) {
        given(isAlive());
        then(purchase(costOf(unit), events), onTrainStarted(events));
        then(startProducing(trainProgress(unit), trainTime(unit)));
        thenUpdate(create(unit, events));
        then(moveAdjacentSubject(), onTrainCompleted(events), play(Target, Ready));
    }

    private float trainProgress(UnitType unit) {
        Building building = (Building)getItem();
        if (building.isProducing()) {
            return building.getProductionProgress() * trainTime(unit);
        }
        return 0;
    }
}