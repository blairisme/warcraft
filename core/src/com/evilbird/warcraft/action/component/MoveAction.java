/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.component;

import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.pathing.ManhattanHeuristic;
import com.evilbird.engine.common.lang.Positionable;
import com.evilbird.engine.item.*;
import com.evilbird.warcraft.item.common.capability.Movable;
import com.evilbird.warcraft.item.layer.LayerType;

import java.util.Iterator;

/**
 * Instances of this {@link BasicAction action} move an item from their current
 * location to a given destination. The moving item will be animated with a
 * movement animation, as well choose a path that avoids obstacles.
 *
 * @author Blair Butterworth
 */
//TODO: Don't allow two units to occupy the same node (not always but under certain conditions)
//TODO: Handle target moving away (dont recalculate path every update )
//TODO: Move to close point adjacent to item destination, not the bottom left (change in spatial graph possibly).
//TODO: Newly created items are not avoided (not added to spatial graph)
public class MoveAction extends BasicAction
{
    private Movable target;
    private MoveDestination destination;
    private MovePathFilter filter;
    private SpatialGraph graph;
    private GraphPath<SpatialItemNode> path;
    private Iterator<SpatialItemNode> pathIterator;
    private SpatialItemNode pathNode;

    public MoveAction(Movable target, Vector2 destination) {
        this(target, new MoveDestinationVector(destination));
    }

    public MoveAction(Movable target, Item destination) {
        this(target, new MoveDestinationItem(destination));
    }

    private MoveAction(Movable target, MoveDestination destination) {
        this.target = target;
        this.destination = destination;
        this.filter = new MovePathFilter(target);
    }

    @Override
    public void restart() {
        path = null;
    }

    @Override
    public boolean act(float time) {
        if (! isValid()) {
            restart();
        }
        if (! load()) {
            return true;
        }
        if (! update(time)) {
            finish();
            return true;
        }
        return false;
    }

    private boolean isValid() {
        return isPathNodeValid() && isDestinationValid();
    }

    private boolean isPathNodeValid() {
        if (pathNode != null && ! filter.test(pathNode)) {
            return false;
        }
        return true;
    }

    private boolean isDestinationValid() {
        return destination.isDestinationValid();
    }

    private boolean load() {
        return loadGraph() && loadPath();
    }

    private boolean loadGraph() {
        if (graph == null) {
            ItemRoot root = target.getRoot();
            SpatialGraph rootGraph = root.getSpatialGraph();
            graph = new SpatialGraph(rootGraph, filter);
        }
        return true;
    }

    private boolean loadPath() {
        if (path == null) {
            SpatialItemNode start = graph.getNode(target.getPosition());
            SpatialItemNode end = destination.getDestinationNode(graph);
            IndexedAStarPathFinder<SpatialItemNode> pathFinder = new IndexedAStarPathFinder<>(graph);

            Heuristic<SpatialItemNode> heuristic = new ManhattanHeuristic();
            GraphPath<SpatialItemNode> result = new DefaultGraphPath<>();
            if (pathFinder.searchNodePath(start, end, heuristic, result)) {
                path = result;
                pathIterator = path.iterator();
                pathNode = start;
                return initializePathNode();
            }
        }
        return path != null;
    }

    private boolean initializePathNode() {
        if (path.getCount() == 0) {
            return false;
        }
        if (path.getCount() > 0) {
            incrementNode();
        }
        if (path.getCount() > 1) {
            incrementNode();
        }
        return true;
    }

    private boolean update(float time) {
        Vector2 position = target.getPosition();
        return incrementPath(position) && updatePosition(position, time);
    }

    private boolean incrementPath(Vector2 targetPosition) {
        Vector2 nodePosition = pathNode.getWorldReference();

        if (targetPosition.equals(nodePosition)) {
            if (pathIterator.hasNext()) {
                incrementNode();
            }
            else {
                return false;
            }
        }
        return true;
    }

    private void incrementNode() {
        graph.removeOccupants(pathNode, (Item)target);
        pathNode = pathIterator.next();
        graph.addOccupants(pathNode, (Item)target);
    }

    private boolean updatePosition(Vector2 oldPosition, float time) {
        Vector2 newPosition = getNextPosition(oldPosition, time);
        target.setPosition(newPosition);
        return true;
    }

    private Vector2 getNextPosition(Vector2 position, float time) {
        Vector2 pathNodePosition = pathNode.getWorldReference();
        Vector2 remaining = pathNodePosition.cpy().sub(position);
        float remainingDistance = remaining.len();
        float incrementDistance = time * target.getMovementSpeed();

        if (remainingDistance > incrementDistance) {
            Vector2 direction = remaining.nor();
            Vector2 increment = direction.scl(incrementDistance);
            return position.cpy().add(increment);
        }
        return pathNodePosition;
    }

    private void finish() {
        target.setDirection(destination.getOrientationTarget().cpy().nor());
    }
}
