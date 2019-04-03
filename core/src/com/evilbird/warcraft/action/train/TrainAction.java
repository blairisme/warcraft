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

import static com.evilbird.warcraft.action.common.production.CreateAction.create;
import static com.evilbird.warcraft.action.common.production.ProduceAction.produce;
import static com.evilbird.warcraft.action.common.resource.ResourceTransferAction.purchase;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;

/**
 * Instances of this action sequence create a new unit, decrementing the
 * players resources and adding delay before the new unit can be used.
 *
 * @author Blair Butterworth
 */
public class TrainAction extends ScenarioAction<TrainActions>
{
    private transient TrainReporter events;

    @Inject
    public TrainAction(TrainReporter observer) {
        this.events = observer;
    }

    @Override
    protected void steps(TrainActions type) {
        scenario(type);
        given(isAlive());
        then(purchase(type, events));
        then(produce(type));
        then(create(type, events));
    }
}