/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.production.produce;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.evilbird.warcraft.behaviour.ai.common.guard.ConditionWait;
import com.evilbird.warcraft.object.unit.building.Building;

import javax.inject.Inject;

/**
 * A {@link Sequence} implementation defining the steps required by unit and
 * upgrade production behaviour. Namely to select a building for production and
 * then to execute the production action.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("unchecked")
public class ProduceSequence extends Sequence<ProduceData>
{
    @Inject
    public ProduceSequence(SelectFacility selectFacility, ProduceTask createProduct) {
        super(selectFacility, createProduct, waitForProduction());
    }

    private static Task<ProduceData> waitForProduction() {
        return new ConditionWait<ProduceData, Building>()
            .from(ProduceData::getProducer)
            .when(Building::isProducing);
    }
}
