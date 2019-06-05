/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.action.common.path.ItemPathFinder;
import com.evilbird.warcraft.item.common.movement.Movable;

import java.util.Iterator;

/**
 * Instances of this {@link Action action} move an {@link Item} from its
 * current location to a given destination. The moving item will be animated
 * with a movement animation, as well choose a path that avoids obstacles.
 *
 * @author Blair Butterworth
 */
abstract class MoveAction extends BasicAction
{
    private Events events;
    private ItemGraph graph;
    private ItemNode endNode;
    private ItemNode pathNode;
    private GraphPath<ItemNode> path;
    private Iterator<ItemNode> pathIterator;

    MoveAction() {
    }

    public void setObserver(Events events) {
        this.events = events;
    }

    @Override
    public void reset() {
        path = null;
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
        return pathNode != endNode || getPathFilter().test(endNode);
    }

    private boolean isValid() {
        return isNextNodeValid() && isLastNodeValid();
    }

    private boolean isNextNodeValid() {
         return getPathFilter().test(pathNode);
    }

    private boolean isLastNodeValid() {
        return getDestination().isDestinationValid(graph, endNode);
    }

    private boolean load() {
        return loadGraph() && loadPath();
    }

    private boolean loadGraph() {
        if (graph == null) {
            Item item = getItem();
            ItemRoot root = item.getRoot();
            ItemGraph rootGraph = root.getSpatialGraph();
            graph = new ItemGraph(rootGraph, getPathFilter());
        }
        return true;
    }

    private boolean loadPath() {
        if (path == null) {
            ItemNode startNode = graph.getNode(getItem().getPosition());
            endNode = getDestination().getDestinationNode(graph, startNode);
            path = ItemPathFinder.findPath(graph, startNode, endNode);

            if (path != null && endNode != null && startNode != null) {
                pathIterator = path.iterator();
                pathNode = startNode;
                clearAdjacentNodes();
                setInitialNode();
                return true;
            }
        }
        return path != null;
    }

    private void clearAdjacentNodes() {
        Item item = getItem();
        for (ItemNode node : graph.getAdjacentNodes(item)) {
            node.removeOccupant(item);
        }
    }

    private void setInitialNode() {
        if (path.getCount() != 0) {
            if (path.getCount() == 1) {
                incrementNode();
            }
            else {
                //ignore moving to current node
                incrementNode();
                incrementNode();
            }
        }
    }

    private boolean update(float time) {
        Item item = getItem();
        Vector2 position = item.getPosition();
        return updatePath(position) && updatePosition(item, position, time);
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
        if (!getDestination().isDestinationReached(graph, nextNode)) {
            Item item = getItem();
            graph.removeOccupants(pathNode, item);
            pathNode = nextNode;
            graph.addOccupants(pathNode, item);
            notifyMove(pathNode, item);
            return true;
        }
        return false;
    }

    private boolean updatePosition(Item item, Vector2 oldPosition, float time) {
        Vector2 newPosition = getNextPosition(item, oldPosition, time);
        item.setPosition(newPosition);
        return true;
    }

    private Vector2 getNextPosition(Item item, Vector2 position, float time) {
        Movable movable = (Movable)item;

        Vector2 pathNodePosition = pathNode.getWorldReference();
        Vector2 remaining = pathNodePosition.cpy().sub(position);
        float remainingDistance = remaining.len();
        float incrementDistance = time * movable.getMovementSpeed();

        if (remainingDistance > incrementDistance) {
            Vector2 direction = remaining.nor();
            Vector2 increment = direction.scl(incrementDistance);
            return position.cpy().add(increment);
        }
        return pathNodePosition;
    }

    private void error() {
        setError(new PathUnknownException(getItem()));
        events.add(new MoveEvent(getItem(), MoveStatus.Failed));
    }

    private void complete() {
        events.add(new MoveEvent(getItem(), MoveStatus.Complete));
    }

    private void notifyMove(ItemNode location, Item subject) {
        events.add(new MoveEvent(subject, location, MoveStatus.Updated));
    }

    protected abstract ItemPathFilter getPathFilter();

    protected abstract MoveDestination getDestination();
}
