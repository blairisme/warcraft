/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.common.path;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.evilbird.engine.common.pathing.ManhattanHeuristic;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.object.common.capability.MovableObject;

/**
 * Instances of this class calculate {@link GraphPath path} between {@link GameObject
 * Items} belonging to a {@link GameObjectGraph}.
 *
 * @author Blair Butterworth
 */
public class ItemPathFinder
{
    /**
     * Disable construction of this static helper class.
     */
    private ItemPathFinder() {
    }

    /**
     * Returns a path that connects the given objects and conforms to given
     * objects traversal capability.
     */
    public static ItemNodePath findPath(GameObjectGraph graph, GameObjectNode start, GameObjectNode end) {
        ItemNodePath result = new ItemNodePath();
        PathFinder<GameObjectNode> pathFinder = new IndexedAStarPathFinder<>(graph);
        Heuristic<GameObjectNode> heuristic = new ManhattanHeuristic<>();
        pathFinder.searchNodePath(start, end, heuristic, result);
        return result;
    }

    /**
     * Determines if a path exists between the given objects that conforms to
     * given objects traversal capability.
     */
    public static boolean hasPath(MovableObject from, GameObject to) {
        ItemPathFilter filter = new ItemPathFilter();
        filter.addTraversableItem(from);
        filter.addTraversableItem(to);
        filter.addTraversableCapability(from.getMovementCapability());

        GameObjectContainer root = from.getRoot();
        GameObjectGraph graph = root.getSpatialGraph();
        GameObjectGraph filteredGraph = new GameObjectGraph(graph, filter);
        GameObjectNode start = graph.getNode(from.getPosition());

        for (GameObjectNode end: graph.getAdjacentNodes(to)) {
            GraphPath<GameObjectNode> path = findPath(filteredGraph, start, end);
            if (path.getCount() > 0) {
                return true;
            }
        }
        return false;
    }
}
