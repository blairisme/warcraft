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
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;
import java.util.Collection;
import java.util.function.Predicate;

import static com.evilbird.warcraft.behaviour.ai.gather.GatherPredicates.AvailableLandGatherers;
import static com.evilbird.warcraft.behaviour.ai.gather.GatherPredicates.AvailableSeaGatherers;
import static com.evilbird.warcraft.behaviour.ai.gather.GatherPredicates.Gatherers;

/**
 * A {@link LeafTask} implementation that selects a gatherer who will obtain
 * resources for the player.
 *
 * @author Blair Butterworth
 */
public class SelectGatherer extends LeafTask<GatherData>
{
    @Inject
    public SelectGatherer() {
    }

    @Override
    public Status execute() {
        GatherData data = getObject();
        GameObject gatherer = availableGatherer(data);
        data.setGatherer(gatherer);
        return gatherer != null ? Status.SUCCEEDED : Status.FAILED;
    }

    private GameObject availableGatherer(GatherData data) {
        Player player = data.getPlayer();
        Collection<GameObject> gatherers = player.findAll(Gatherers);

        ResourceType type = data.getResource();
        Predicate<GameObject> availability = availabilityFilter(type);

        return CollectionUtils.findFirst(gatherers, availability);
    }

    public Predicate<GameObject> availabilityFilter(ResourceType type) {
        switch (type) {
            case Gold:
            case Wood: return AvailableLandGatherers;
            case Oil: return AvailableSeaGatherers;
            default: throw new UnsupportedOperationException();
        }
    }

    @Override
    protected Task<GatherData> copyTo(Task<GatherData> task) {
        return task;
    }
}
