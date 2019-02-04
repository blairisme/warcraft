/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.pathing.ManhattanHeuristic;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGraph;
import com.evilbird.engine.item.ItemNode;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.common.capability.Movable;

import javax.inject.Inject;
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
    private MoveObserver observer;
    private ItemGraph graph;
    private GraphPath<ItemNode> path;
    private Iterator<ItemNode> pathIterator;
    private ItemNode pathNode;
    private ItemNode endNode;

    public MoveAction() {
    }

    public MoveAction(Movable target, Vector2 destination) {
        setItem(target);
        setDestination(destination);
    }

    public MoveAction(Movable target, Item destination) {
        setItem(target);
        setDestination(destination);
    }

    public void setItem(Movable target) {
        this.target = target;
        this.path = null;
    }

    public void setDestination(Vector2 destination) {
        this.destination = new MoveDestinationVector(destination);
        this.filter = new MovePathFilter(target);
        this.path = null;
    }

    public void setDestination(Item destination) {
        this.destination = new MoveDestinationItem(destination);
        this.filter = new MovePathFilter(target, destination);
        this.path = null;
    }

    public void setObserver(MoveObserver observer) {
        this.observer = observer;
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
        if (!update(time)) {
            complete();
            return true;
        }
        if (!isValid()) {
            restart();
            return false;
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
        return destination.isDestinationValid(graph, endNode);
    }

    private boolean load() {
        return loadGraph() && loadPath();
    }

    private boolean loadGraph() {
        if (graph == null) {
            ItemRoot root = target.getRoot();
            ItemGraph rootGraph = root.getSpatialGraph();
            graph = new ItemGraph(rootGraph, filter);
        }
        return true;
    }

    private boolean loadPath() {
        if (path == null) {
            ItemNode startNode = graph.getNode(target.getPosition());
            endNode = destination.getDestinationNode(graph, startNode);
            PathFinder<ItemNode> pathFinder = new IndexedAStarPathFinder<>(graph);
            Heuristic<ItemNode> heuristic = new ManhattanHeuristic<>();
            GraphPath<ItemNode> result = new DefaultGraphPath<>();

            if (pathFinder.searchNodePath(startNode, endNode, heuristic, result)) {
                path = result;
                pathIterator = path.iterator();
                pathNode = startNode;
                initializePathNode();
                return true;
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
        ItemNode nextNode = pathIterator.next();
        if (!destination.isDestinationReached(graph, nextNode)) {
            graph.removeOccupants(pathNode, (Item)target);
            pathNode = nextNode;
            graph.addOccupants(pathNode, (Item)target);
            notifyMove(pathNode, (Item)target);
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
        setError(new MoveImpossibleException((Item)target));
    }

    private void complete() {
    }

    private void notifyMove(ItemNode location, Item subject) {
        if (observer != null) {
            observer.onMove(subject, location);
        }
    }
}
