/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.production.train;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.produce.ProduceUnitActions;
import com.evilbird.warcraft.behaviour.ai.common.task.AsyncActionTask;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;

/**
 * A task that assigns a unit production action to the subject contained in the
 * tasks blackboard.
 *
 * @author Blair Butterworth
 */
public class TrainTask extends AsyncActionTask<TrainData>
{
    @Inject
    public TrainTask(ActionFactory factory) {
        super(factory);
    }

    @Override
    protected Action getAction(ActionFactory factory) {
        TrainData data = getObject();
        UnitType product = data.getProduct();
        GameObject producer = data.getProducer();

        Action produce = factory.get(ProduceUnitActions.forProduct(product));
        produce.setSubject(producer);

        producer.removeActions();
        producer.addAction(produce);

        return produce;
    }
}
