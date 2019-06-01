/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.path;

import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.evilbird.engine.common.pathing.ManhattanHeuristic;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.item.common.movement.Movable;

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

    public static GraphPath<ItemNode> findPath(ItemGraph graph, ItemNode start, ItemNode end) {
        PathFinder<ItemNode> pathFinder = new IndexedAStarPathFinder<>(graph);
        Heuristic<ItemNode> heuristic = new ManhattanHeuristic<>();
        GraphPath<ItemNode> result = new DefaultGraphPath<>();

        if (pathFinder.searchNodePath(start, end, heuristic, result)) {
            return result;
        }
        return null;
    }

    public static GraphPath<ItemNode> findPath(ItemGraph graph, ItemNode start, ItemNode end, ItemPathFilter filter) {
        return findPath(new ItemGraph(graph, filter), start, end);
    }

    public static boolean hasPath(ItemGraph graph, ItemNode start, ItemNode end) {
        PathFinder<ItemNode> pathFinder = new IndexedAStarPathFinder<>(graph);
        Heuristic<ItemNode> heuristic = new ManhattanHeuristic<>();
        GraphPath<ItemNode> result = new DefaultGraphPath<>();
        return pathFinder.searchNodePath(start, end, heuristic, result);
    }

    public static boolean hasPath(ItemGraph graph, ItemNode start, ItemNode end, ItemPathFilter filter) {
        return hasPath(new ItemGraph(graph, filter), start, end);
    }

    public static boolean hasPath(Movable fromItem, Item toItem) {
        ItemPathFilter filter = new ItemPathFilter();
        filter.addTraversableItem(fromItem);
        filter.addTraversableItem(toItem);
        filter.addTraversableCapability(fromItem.getMovementCapability());

        ItemRoot root = fromItem.getRoot();
        ItemGraph graph = root.getSpatialGraph();
        ItemNode start = graph.getNode(fromItem.getPosition());
        ItemNode end = graph.getNode(toItem.getPosition());

        return hasPath(graph, start, end, filter);
    }
}
