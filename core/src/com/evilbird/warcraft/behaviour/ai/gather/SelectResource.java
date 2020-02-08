/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.gather;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.warcraft.action.gather.GatherLocations;
import com.evilbird.warcraft.data.resource.ResourceContainer;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.warcraft.object.unit.UnitType.GoldMine;

/**
 * A {@link LeafTask} implementation that selects a resource from which to
 * obtain resources.
 *
 * @author Blair Butterworth
 */
public class SelectResource extends LeafTask<com.evilbird.warcraft.behaviour.ai.gather.GatherData>
{
    @Inject
    public SelectResource() {
    }

    @Override
    public Status execute() {
        com.evilbird.warcraft.behaviour.ai.gather.GatherData data = getObject();
        ResourceContainer resource = getResource(data);
        data.setResource(resource);
        return resource != null ? Status.SUCCEEDED : Status.FAILED;
    }

    protected ResourceContainer getResource(com.evilbird.warcraft.behaviour.ai.gather.GatherData data) {
        Gatherer gatherer = data.getGatherer();
        return (ResourceContainer)GatherLocations.closestResource(gatherer, gatherer, GoldMine);
    }

    @Override
    protected Task<com.evilbird.warcraft.behaviour.ai.gather.GatherData> copyTo(Task<GatherData> task) {
        throw new UnsupportedOperationException();
    }
}
