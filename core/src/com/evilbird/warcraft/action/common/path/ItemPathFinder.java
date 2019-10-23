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
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.item.common.capability.MovableObject;

/**
 * Instances of this class calculate {@link GraphPath path} between {@link Item
 * Items} belonging to a {@link ItemGraph}.
 *
 * @author Blair Butterworth
 */
public class ItemPathFinder
{
    private ItemPathFinder() {
    }

    public static ItemNodePath findPath(ItemGraph graph, ItemNode start, ItemNode end) {
        ItemNodePath result = new ItemNodePath();
        PathFinder<ItemNode> pathFinder = new IndexedAStarPathFinder<>(graph);
        Heuristic<ItemNode> heuristic = new ManhattanHeuristic<>();
        pathFinder.searchNodePath(start, end, heuristic, result);
        return result;
    }

    public static boolean hasPath(MovableObject fromItem, Item toItem) {
        ItemPathFilter filter = new ItemPathFilter();
        filter.addTraversableItem(fromItem);
        filter.addTraversableItem(toItem);
        filter.addTraversableCapability(fromItem.getMovementCapability());

        ItemRoot root = fromItem.getRoot();
        ItemGraph graph = root.getSpatialGraph();
        ItemGraph filteredGraph = new ItemGraph(graph, filter);
        ItemNode start = graph.getNode(fromItem.getPosition());

        for (ItemNode end: graph.getAdjacentNodes(toItem)) {
            GraphPath<ItemNode> path = findPath(filteredGraph, start, end);
            if (path.getCount() > 0) {
                return true;
            }
        }
        return false;
    }
}
