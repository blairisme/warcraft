/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.production.construct;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.evilbird.engine.behaviour.framework.guard.ConditionWait;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;

/**
 * A {@link Sequence} implementation defining the steps required by the
 * construct behaviour. Namely to select a building for construction, a nearby
 * builder, an unoccupied building location and then to execute the construct
 * action.
 *
 * @author Blair Butterworth
 */
public class ConstructSequence extends Sequence<ConstructData>
{
    @Inject
    public ConstructSequence(
        SelectBuilder selectBuilder,
        SelectLocation selectLocation,
        ConstructTask constructBuilding)
    {
        super(selectBuilder,
              selectLocation,
              constructBuilding,
              waitForConstruction());
    }

    private static Task<ConstructData> waitForConstruction() {
        return new ConditionWait<ConstructData, Gatherer>()
            .from(ConstructData::getBuilder)
            .when(Gatherer::isConstructing);
    }
}
