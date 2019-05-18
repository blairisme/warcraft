/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.action.framework.ScenarioAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.common.resource.ResourceTransferAction.deposit;
import static com.evilbird.warcraft.action.train.TrainAction.stopProducing;
import static com.evilbird.warcraft.action.train.TrainEvents.onTrainCancelled;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isProducing;
import static com.evilbird.warcraft.item.unit.UnitCosts.costOf;

/**
 * Instances of this class stop the training of an {@link Item}, refunding a
 * proportion of the cost of training it.
 *
 * @author Blair Butterworth
 */
public class TrainCancel extends ScenarioAction<TrainActions>
{
    private transient TrainReporter reporter;

    /**
     * Constructs a new instance of this class given a {@link TrainReporter}
     * used to report the transfer of resources involved in the refund for the
     * partially complete train operation.
     *
     * @param reporter  a {@code TrainReporter} instance. This parameter
     *                  cannot be {@code null}.
     */
    @Inject
    public TrainCancel(TrainReporter reporter) {
        this.reporter = reporter;
    }

    @Override
    protected void steps(TrainActions action) {
        scenario(action);
        steps(action.getUnitType());
    }

    protected void steps(UnitType unit) {
        given(isProducing());
        then(stopProducing(), deposit(costOf(unit), reporter), onTrainCancelled(reporter));
    }
}