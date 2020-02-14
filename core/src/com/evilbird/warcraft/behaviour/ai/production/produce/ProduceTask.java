/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.production.produce;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.framework.task.AsyncActionTask;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.produce.ProduceActions;
import com.evilbird.warcraft.data.product.Product;

import javax.inject.Inject;

/**
 * A task that assigns a production action to the subject contained in the
 * tasks blackboard.
 *
 * @author Blair Butterworth
 */
public class ProduceTask extends AsyncActionTask<ProduceData>
{
    @Inject
    public ProduceTask(ActionFactory factory) {
        super(factory);
    }

    @Override
    protected Action getAction(ActionFactory factory) {
        ProduceData data = getObject();
        Product product = data.getProduct();
        GameObject producer = data.getProducer();

        Action produce = factory.get(ProduceActions.forProduct(product));
        produce.setSubject(producer);

        producer.removeActions();
        producer.addAction(produce);

        return produce;
    }
}
