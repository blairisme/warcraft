/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.wander;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import org.apache.commons.lang3.RandomUtils;

import javax.inject.Inject;
import java.util.List;

import static com.evilbird.warcraft.action.common.spatial.SpatialLocationUtils.getDestinations;

/**
 * A {@link LeafTask} that selects a random destination for the subject,
 * contained in the blackboard, to move to.
 *
 * @author Blair Butterworth
 */
public class SelectDestination extends LeafTask<WanderData>
{
    private static final int MOVE_RADIUS = 3;
    private static final int UNINITIALIZED = -1;

    private int distance = UNINITIALIZED;

    @Inject
    public SelectDestination() {
    }

    @Override
    protected Task<WanderData> copyTo(Task<WanderData> task) {
        return task;
    }

    @Override
    public Status execute() {
        WanderData data = getObject();
        GameObjectNode destination = getDestination(data);

        if (destination != null) {
            data.setDestination(destination.getWorldReference());
            return Status.SUCCEEDED;
        }
        return Status.FAILED;
    }

    private GameObjectNode getDestination(WanderData data) {
        MovableObject subject = data.getSubject();
        List<GameObjectNode> destinations = getDestinations(subject, getDistance(subject));

        if (! destinations.isEmpty()) {
            return destinations.get(RandomUtils.nextInt(0, destinations.size()));
        }
        return null;
    }

    private int getDistance(MovableObject subject) {
        if (distance == UNINITIALIZED) {
            GameObjectContainer container = subject.getRoot();
            GameObjectGraph graph = container.getSpatialGraph();
            distance = graph.getNodeWidth() * MOVE_RADIUS;
        }
        return distance;
    }
}
