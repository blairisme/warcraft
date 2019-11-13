/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
    private ItemPathFinder() {
    }

    public static ItemNodePath findPath(GameObjectGraph graph, GameObjectNode start, GameObjectNode end) {
        ItemNodePath result = new ItemNodePath();
        PathFinder<GameObjectNode> pathFinder = new IndexedAStarPathFinder<>(graph);
        Heuristic<GameObjectNode> heuristic = new ManhattanHeuristic<>();
        pathFinder.searchNodePath(start, end, heuristic, result);
        return result;
    }

    public static boolean hasPath(MovableObject fromItem, GameObject toGameObject) {
        ItemPathFilter filter = new ItemPathFilter();
        filter.addTraversableItem(fromItem);
        filter.addTraversableItem(toGameObject);
        filter.addTraversableCapability(fromItem.getMovementCapability());

        GameObjectContainer root = fromItem.getRoot();
        GameObjectGraph graph = root.getSpatialGraph();
        GameObjectGraph filteredGraph = new GameObjectGraph(graph, filter);
        GameObjectNode start = graph.getNode(fromItem.getPosition());

        for (GameObjectNode end: graph.getAdjacentNodes(toGameObject)) {
            GraphPath<GameObjectNode> path = findPath(filteredGraph, start, end);
            if (path.getCount() > 0) {
                return true;
            }
        }
        return false;
    }
}
