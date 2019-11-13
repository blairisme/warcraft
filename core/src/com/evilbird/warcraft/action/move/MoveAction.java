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
import com.evilbird.engine.object.AnimatedObject;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.spatial.SpatialObject;
import com.evilbird.warcraft.action.common.path.ItemNodePath;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.action.common.path.ItemPathFinder;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.unit.UnitAnimation;

import java.util.Collection;
import java.util.ListIterator;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.engine.common.collection.CollectionUtils.filter;
import static com.evilbird.engine.common.pathing.SpatialUtils.getClosest;

/**
 * Instances of this {@link Action action} move an {@link GameObject} from its
 * current location to a given destination. The moving item will be animated
 * with a movement animation, as well choose a path that avoids obstacles.
 *
 * @author Blair Butterworth
 */
abstract class MoveAction extends BasicAction
{
    protected Events events;
    protected GameObjectGraph graph;
    protected GameObjectNode waypoint;
    protected GameObjectNode endpoint;
    protected ItemNodePath path;
    protected ListIterator<GameObjectNode> pathIterator;

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
        GameObject gameObject = getSubject();
        return move(gameObject, time);
    }

    public boolean move(GameObject gameObject, float time) {
        if (!initialize(gameObject)) {
            return moveFailed(gameObject);
        }
        if (! waypointReached(gameObject)) {
            return updatePosition(gameObject, time);
        }
        if (destinationReached() || lastWaypointReached()) {
            return moveComplete(gameObject);
        }
        if (!destinationValid() || !nextWaypointValid()) {
            return reinitializePath(gameObject);
        }
        return updateWaypoint(gameObject);
    }

    protected boolean moveFailed(GameObject gameObject) {
        setError(new PathUnknownException(gameObject));
        events.add(new MoveEvent(gameObject, MoveStatus.Failed));
        return ActionComplete;
    }

    protected boolean moveComplete(GameObject gameObject) {
        resetItem(gameObject);
        events.add(new MoveEvent(gameObject, MoveStatus.Complete));
        return ActionComplete;
    }

    protected boolean reinitializePath(GameObject gameObject) {
        resetItem(gameObject);
        restart();
        return ActionIncomplete;
    }

    protected void resetItem(GameObject gameObject) {
        updateOccupancy(gameObject);
        if (gameObject instanceof AnimatedObject) {
            AnimatedObject animatedObject = (AnimatedObject) gameObject;
            animatedObject.setAnimation(UnitAnimation.Idle);
        }
    }

    private boolean destinationReached() {
        return destinationReached(waypoint);
    }

    protected boolean destinationReached(GameObjectNode node) {
        return true;
    }

    protected boolean destinationValid() {
        return true;
    }

    protected boolean lastWaypointReached() {
        return waypoint.equals(endpoint);
    }

    protected boolean waypointReached(GameObject gameObject) {
        Vector2 position = gameObject.getPosition();
        Vector2 nodePosition = waypoint.getWorldReference();
        return position.equals(nodePosition);
    }

    protected boolean nextWaypointValid() {
        if (pathIterator.nextIndex() < path.getCount()) {
            GameObjectNode nextNode = path.get(pathIterator.nextIndex());
            ItemPathFilter pathFilter = getPathFilter();
            return pathFilter.test(nextNode);
        }
        return false;
    }

    protected abstract ItemPathFilter getPathFilter();

    protected abstract Vector2 getDestination();

    protected boolean initialize(GameObject gameObject) {
        if (path == null) {
            return initializeGraph(gameObject)
                && initializeStartNode(gameObject)
                && initializePath(gameObject)
                && initializeItem(gameObject);
        }
        return true;
    }

    protected boolean initializeGraph(GameObject gameObject) {
        if (graph == null) {
            GameObjectContainer root = gameObject.getRoot();
            graph = new GameObjectGraph(root.getSpatialGraph(), getPathFilter());
        }
        return true;
    }

    protected boolean initializeStartNode(GameObject gameObject) {
        if (waypoint == null) {
            waypoint = getStartNode(gameObject);
        }
        return waypoint != null;
    }

    protected GameObjectNode getStartNode(GameObject gameObject) {
        if (graph.isPartiallyAligned(gameObject)) {
            return getAdjacentNode(gameObject);
        }
        return graph.getNode(gameObject.getPosition());
    }

    protected GameObjectNode getAdjacentNode(GameObject gameObject) {
        GameObjectNode destinationNode = graph.getNode(getDestination());
        if (destinationNode != null) {
            Collection<GameObjectNode> adjacentNodes = graph.getAdjacentNodes(gameObject);
            Collection<GameObjectNode> traversableNodes = filter(adjacentNodes, getPathFilter());
            if (!traversableNodes.isEmpty()) {
                return getClosest(traversableNodes, destinationNode);
            }
        }
        return null;
    }

    protected boolean initializePath(GameObject gameObject) {
        if (path == null) {
            endpoint = getEndNode(waypoint);
            path = ItemPathFinder.findPath(graph, waypoint, endpoint);
            pathIterator = path.listIterator();

            if (!path.isEmpty()) {
                updateOccupancy(gameObject);
                graph.addOccupants(waypoint, gameObject);
            }
        }
        return !path.isEmpty();
    }

    protected GameObjectNode getEndNode(GameObjectNode node) {
        Vector2 destination = getDestination();
        return graph.getNode(destination);
    }

    protected boolean initializeItem(GameObject gameObject) {
        if (gameObject instanceof AnimatedObject) {
            AnimatedObject animatedObject = (AnimatedObject) gameObject;
            animatedObject.setAnimation(UnitAnimation.Move);
        }
        return true;
    }

    protected void updateOccupancy(GameObject gameObject) {
        if (gameObject instanceof SpatialObject) {
            SpatialObject occupant = (SpatialObject) gameObject;

            Collection<GameObjectNode> previouslyOccupiedNodes = graph.getOccupiedNodes(occupant);
            graph.removeOccupants(previouslyOccupiedNodes, occupant);

            Collection<GameObjectNode> currentlyOccupiedNodes = graph.getNodes(occupant);
            graph.addOccupants(currentlyOccupiedNodes, occupant);
        }
    }

    protected boolean updateWaypoint(GameObject gameObject) {
        updateOccupancy(gameObject);
        events.add(new MoveEvent(gameObject, waypoint, MoveStatus.Updated));
        waypoint = pathIterator.next();
        graph.addOccupants(waypoint, gameObject);
        return ActionIncomplete;
    }

    protected boolean updatePosition(GameObject gameObject, float time) {
        Vector2 oldPosition = gameObject.getPosition();
        Vector2 newPosition = getNextPosition(gameObject, oldPosition, time);
        gameObject.setPosition(newPosition);
        return ActionIncomplete;
    }

    protected Vector2 getNextPosition(GameObject gameObject, Vector2 position, float time) {
        MovableObject movable = (MovableObject) gameObject;

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
