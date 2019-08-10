/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

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
import static com.evilbird.warcraft.action.produce.ProduceAction.startProducing;
import static com.evilbird.warcraft.action.produce.ProduceEvents.onProductionCompleted;
import static com.evilbird.warcraft.action.produce.ProduceEvents.onProductionStarted;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.unit.UnitCosts.buildTime;
import static com.evilbird.warcraft.item.unit.UnitCosts.cost;
import static com.evilbird.warcraft.item.unit.UnitSound.Ready;

/**
 * Instances of this action sequence create a new unit, decrementing the
 * players resources and adding delay before the new unit can be used. The new
 * unit will be placed next to the building that created it, in the first
 * unoccupied space.
 *
 * @author Blair Butterworth
 */
public class ProduceUnit extends ScenarioAction<ProduceUnitActions>
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
    public ProduceUnit(EventQueue events) {
        this.events = events;
    }

    @Override
    protected void steps(ProduceUnitActions action) {
        scenario(action);
        steps(action.getProduct());
    }

    private void steps(UnitType unit) {
        given(isAlive());
        then(purchase(cost(unit), events));
        then(onProductionStarted(events));
        then(startProducing(startTime(unit), buildTime(unit)));
        thenUpdate(create(unit, events));
        then(moveAdjacentSubject(), play(Target, Ready));
        then(onProductionCompleted(events));
    }

    private float startTime(UnitType unit) {
        Building building = (Building)getItem();
        if (building.isProducing()) {
            return building.getProductionProgress() * buildTime(unit);
        }
        return 0;
    }
}