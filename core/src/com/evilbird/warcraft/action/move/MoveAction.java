/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemGraphOccupant;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.engine.item.specialized.Viewable;
import com.evilbird.warcraft.action.common.path.ItemNodePath;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.action.common.path.ItemPathFinder;
import com.evilbird.warcraft.item.common.movement.Movable;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import java.util.Collection;
import java.util.ListIterator;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.engine.common.collection.CollectionUtils.filter;
import static com.evilbird.engine.common.pathing.SpatialUtils.getClosest;

/**
 * Instances of this {@link Action action} move an {@link Item} from its
 * current location to a given destination. The moving item will be animated
 * with a movement animation, as well choose a path that avoids obstacles.
 *
 * @author Blair Butterworth
 */
abstract class MoveAction extends BasicAction
{
    protected Events events;
    protected ItemGraph graph;
    protected ItemNode waypoint;
    protected ItemNode endpoint;
    protected ItemNodePath path;
    protected ListIterator<ItemNode> pathIterator;

    public MoveAction(Events events) {
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
        Item item = getItem();
        return move(item, time);
    }

    public boolean move(Item item, float time) {
        if (!initialize(item)) {
            return moveFailed(item);
        }
        if (! waypointReached(item)) {
            return updatePosition(item, time);
        }
        if (destinationReached() || lastWaypointReached()) {
            return moveComplete(item);
        }
        if (!destinationValid() || !nextWaypointValid()) {
            return reinitializePath(item);
        }
        return updateWaypoint(item);
    }

    protected boolean moveFailed(Item item) {
        setError(new PathUnknownException(item));
        events.add(new MoveEvent(item, MoveStatus.Failed));
        return ActionComplete;
    }

    protected boolean moveComplete(Item item) {
        resetItem(item);
        events.add(new MoveEvent(item, MoveStatus.Complete));
        return ActionComplete;
    }

    protected boolean reinitializePath(Item item) {
        resetItem(item);
        restart();
        return ActionIncomplete;
    }

    protected void resetItem(Item item) {
        updateOccupancy(item);
        if (item instanceof Viewable) {
            Viewable viewable = (Viewable)item;
            viewable.setAnimation(UnitAnimation.Idle);
        }
    }

    private boolean destinationReached() {
        return destinationReached(waypoint);
    }

    protected boolean destinationReached(ItemNode node) {
        return true;
    }

    protected boolean destinationValid() {
        return true;
    }

    protected boolean lastWaypointReached() {
        return waypoint.equals(endpoint);
    }

    protected boolean waypointReached(Item item) {
        Vector2 position = item.getPosition();
        Vector2 nodePosition = waypoint.getWorldReference();
        return position.equals(nodePosition);
    }

    protected boolean nextWaypointValid() {
        if (pathIterator.nextIndex() < path.getCount()) {
            ItemNode nextNode = path.get(pathIterator.nextIndex());
            ItemPathFilter pathFilter = getPathFilter();
            return pathFilter.test(nextNode);
        }
        return false;
    }

    protected abstract ItemPathFilter getPathFilter();

    protected abstract Vector2 getDestination();

    protected boolean initialize(Item item) {
        if (path == null) {
            return initializeGraph(item)
                && initializeStartNode(item)
                && initializePath(item)
                && initializeItem(item);
        }
        return true;
    }

    protected boolean initializeGraph(Item item) {
        if (graph == null) {
            ItemRoot root = item.getRoot();
            graph = new ItemGraph(root.getSpatialGraph(), getPathFilter());
        }
        return true;
    }

    protected boolean initializeStartNode(Item item) {
        if (waypoint == null) {
            waypoint = getStartNode(item);
        }
        return waypoint != null;
    }

    protected ItemNode getStartNode(Item item) {
        if (graph.isPartiallyAligned(item)) {
            return getAdjacentNode(item);
        }
        return graph.getNode(item.getPosition());
    }

    protected ItemNode getAdjacentNode(Item item) {
        ItemNode destinationNode = graph.getNode(getDestination());
        if (destinationNode != null) {
            Collection<ItemNode> adjacentNodes = graph.getAdjacentNodes(item);
            Collection<ItemNode> traversableNodes = filter(adjacentNodes, getPathFilter());
            if (!traversableNodes.isEmpty()) {
                return getClosest(traversableNodes, destinationNode);
            }
        }
        return null;
    }

    protected boolean initializePath(Item item) {
        if (path == null) {
            endpoint = getEndNode(waypoint);
            path = ItemPathFinder.findPath(graph, waypoint, endpoint);
            pathIterator = path.listIterator();

            if (!path.isEmpty()) {
                updateOccupancy(item);
                graph.addOccupants(waypoint, item);
            }
        }
        return !path.isEmpty();
    }

    protected ItemNode getEndNode(ItemNode node) {
        Vector2 destination = getDestination();
        return graph.getNode(destination);
    }

    protected boolean initializeItem(Item item) {
        if (item instanceof Viewable) {
            Viewable viewable = (Viewable)item;
            viewable.setAnimation(UnitAnimation.Move);
        }
        return true;
    }

    protected void updateOccupancy(Item item) {
        if (item instanceof ItemGraphOccupant) {
            ItemGraphOccupant occupant = (ItemGraphOccupant)item;

            Collection<ItemNode> previouslyOccupiedNodes = graph.getOccupiedNodes(occupant);
            graph.removeOccupants(previouslyOccupiedNodes, occupant);

            Collection<ItemNode> currentlyOccupiedNodes = graph.getNodes(occupant);
            graph.addOccupants(currentlyOccupiedNodes, occupant);
        }
    }

    protected boolean updateWaypoint(Item item) {
        updateOccupancy(item);
        events.add(new MoveEvent(item, waypoint, MoveStatus.Updated));
        waypoint = pathIterator.next();
        graph.addOccupants(waypoint, item);
        return ActionIncomplete;
    }

    protected boolean updatePosition(Item item, float time) {
        Vector2 oldPosition = item.getPosition();
        Vector2 newPosition = getNextPosition(item, oldPosition, time);
        item.setPosition(newPosition);
        return ActionIncomplete;
    }

    protected Vector2 getNextPosition(Item item, Vector2 position, float time) {
        Movable movable = (Movable)item;

        Vector2 pathNodePosition = waypoint.getWorldReference();
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
}
