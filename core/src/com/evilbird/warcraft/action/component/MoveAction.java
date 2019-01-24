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
import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.pathing.ManhattanHeuristic;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.SpatialGraph;
import com.evilbird.engine.item.SpatialItemNode;
import com.evilbird.warcraft.item.common.capability.Movable;

import java.util.Iterator;

/**
 * Instances of this {@link BasicAction action} move an item from their current
 * location to a given destination. The moving item will be animated with a
 * movement animation, as well choose a path that avoids obstacles.
 *
 * @author Blair Butterworth
 */
public class MoveAction extends BasicAction
{
    private Movable target;
    private MoveDestination destination;
    private MovePathFilter filter;
    private SpatialGraph graph;
    private GraphPath<SpatialItemNode> path;
    private Iterator<SpatialItemNode> pathIterator;
    private SpatialItemNode pathNode;
    private SpatialItemNode endNode;

    public MoveAction(Movable target, Vector2 destination) {
        this(target, new MoveDestinationVector(destination), new MovePathFilter(target));
    }

    public MoveAction(Movable target, Item destination) {
        this(target, new MoveDestinationItem(destination), new MovePathFilter(target, destination));
    }

    private MoveAction(Movable target, MoveDestination destination, MovePathFilter filter) {
        this.target = target;
        this.destination = destination;
        this.filter = filter;
    }

    @Override
    public void restart() {
        path = null;
    }

    @Override
    public boolean act(float time) {
        if (!load()) {
            error();
            return true;
        }
        if (!isCompletable()) {
            error();
            return true;
        }
        if (!isValid()) {
            restart();
            return false;
        }
        if (!update(time)) {
            complete();
            return true;
        }
        return false;
    }

    private boolean isCompletable() {
        return pathNode != endNode || filter.test(endNode);
    }

    private boolean isValid() {
        return isNextNodeValid() && isLastNodeValid();
    }

    private boolean isNextNodeValid() {
         return filter.test(pathNode);
    }

    private boolean isLastNodeValid() {
        return destination.isDestinationValid(graph);
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
            SpatialItemNode startNode = graph.getNode(target.getPosition());
            endNode = destination.getDestinationNode(graph, startNode);
            PathFinder<SpatialItemNode> pathFinder = new IndexedAStarPathFinder<>(graph);
            Heuristic<SpatialItemNode> heuristic = new ManhattanHeuristic();
            GraphPath<SpatialItemNode> result = new DefaultGraphPath<>();

            if (pathFinder.searchNodePath(startNode, endNode, heuristic, result)) {
                path = result;
                pathIterator = path.iterator();
                pathNode = startNode;
                return initializePathNode();
            }
        }
        return path != null;
    }

    private boolean initializePathNode() {
        if (path.getCount() == 0) {
            return false;
        }
        else if (path.getCount() == 1) {
            return incrementNode();
        }
        else {
            incrementNode(); //ignore partial cell
            return incrementNode();
        }
    }

    private boolean update(float time) {
        Vector2 position = target.getPosition();
        return updatePath(position) && updatePosition(position, time);
    }

    private boolean updatePath(Vector2 targetPosition) {
        Vector2 nodePosition = pathNode.getWorldReference();
        if (targetPosition.equals(nodePosition)) {
            return incrementPath();
        }
        return true;
    }

    private boolean incrementPath() {
        if (pathIterator.hasNext()) {
            return incrementNode();
        }
        return false;
    }

    private boolean incrementNode() {
        SpatialItemNode nextNode = pathIterator.next();
        if (!destination.isDestinationReached(nextNode)) {
            graph.removeOccupants(pathNode, (Item) target);
            pathNode = nextNode;
            graph.addOccupants(pathNode, (Item) target);
            return true;
        }
        return false;
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

    private void error() {
    }

    private void complete() {
        Vector2 targetPosition = target.getPosition();
        Vector2 destinationPosition = destination.getOrientationTarget().cpy();
        Vector2 direction = destinationPosition.sub(targetPosition);
        Vector2 normalizedDirection = direction.nor();
        target.setDirection(normalizedDirection);
    }
}
