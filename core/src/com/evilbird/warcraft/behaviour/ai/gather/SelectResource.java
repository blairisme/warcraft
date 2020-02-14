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
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;
import java.util.Collection;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;
import static com.evilbird.engine.common.collection.CollectionUtils.filter;
import static com.evilbird.warcraft.behaviour.ai.gather.GatherPredicates.Gatherers;
import static com.evilbird.warcraft.behaviour.ai.gather.GatherPredicates.LivingGatherers;

/**
 * A {@link LeafTask} implementation that selects a resource from which to
 * obtain resources.
 *
 * @author Blair Butterworth
 */
public class SelectResource extends LeafTask<GatherData>
{
    @Inject
    public SelectResource() {
    }

    @Override
    public Status execute() {
        GatherData data = getObject();

        Player player = data.getPlayer();
        Collection<GameObject> gatherers = player.findAll(Gatherers);
        Collection<GameObject> livingGatherers = filter(gatherers,  LivingGatherers);

        GatherOrder order = data.getOrder();
        ResourceType resource = order.getNextResource(livingGatherers);

        data.setResource(resource);
        return resource != null ? SUCCEEDED : FAILED;
    }

    @Override
    protected Task<GatherData> copyTo(Task<GatherData> task) {
        return task;
    }
}
