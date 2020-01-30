/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.common.spatial;

import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.object.common.capability.MovableObject;

import java.util.Collection;
import java.util.List;

/**
 * Provides methods for obtaining {@link GameObjectNode nodes} in a
 * {@link GameObjectGraph}.
 *
 * @author Blair Butterworth
 */
public class SpatialLocationUtils
{
    private SpatialLocationUtils() {
    }

    /**
     * Returns a {@link List} of those {@link GameObjectNode nodes} the
     * given subject can move. The resulting nodes will be the provided
     * distance away from the subject.
     */
    public static List<GameObjectNode> getDestinations(MovableObject subject, int distance) {
        GameObjectContainer container = subject.getRoot();
        GameObjectGraph graph = container.getSpatialGraph();
        return getDestinations(graph, subject, distance);
    }

    /**
     * Returns a {@link List} of those {@link GameObjectNode nodes} the
     * given subject can move. The resulting nodes will be the provided
     * distance away from the subject.
     */
    public static List<GameObjectNode> getDestinations(GameObjectGraph graph, MovableObject subject, int distance) {
        ItemPathFilter capability = new ItemPathFilter();
        capability.addTraversableCapability(subject.getMovementCapability());

        Collection<GameObjectNode> adjacent = graph.getAdjacentNodes(subject, distance);
        return CollectionUtils.filter(adjacent, capability);
    }
}
