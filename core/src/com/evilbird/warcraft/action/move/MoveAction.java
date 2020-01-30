/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.AnimatedObject;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.spatial.SpatialObject;
import com.evilbird.warcraft.action.common.spatial.GameObjectPath;
import com.evilbird.warcraft.action.common.spatial.ItemPathFilter;
import com.evilbird.warcraft.action.common.spatial.SpatialPathUtils;
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
public abstract class MoveAction extends BasicAction
{
    protected transient MoveEvents events;
    protected transient GameObjectGraph graph;
    protected transient GameObjectNode waypoint;
    protected transient GameObjectNode endpoint;
    protected transient GameObjectPath path;
    protected transient ListIterator<GameObjectNode> pathIterator;

    public MoveAction(MoveEvents events) {
        this.events = events;
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

    @Override
    public void reset() {
        super.reset();
        path = null;
        graph = null;
        waypoint = null;
        endpoint = null;
        resetSubject();
    }

    @Override
    public void restart() {
        super.restart();
        path = null;
        waypoint = null;
        endpoint = null;
        resetSubject();
    }

    protected boolean moveFailed(GameObject gameObject) {
        resetSubject(gameObject);
        setError(new PathUnknownException(gameObject));
        events.moveFailed(gameObject);
        return ActionComplete;
    }

    protected boolean moveComplete(GameObject gameObject) {
        updateOccupancy(gameObject);
        resetSubject(gameObject);
        events.moveComplete(gameObject);
        return ActionComplete;
    }

    protected boolean reinitializePath(GameObject gameObject) {
        updateOccupancy(gameObject);
        restart();
        return ActionIncomplete;
    }

    protected void resetSubject() {
        GameObject subject = getSubject();
        resetSubject(subject);
    }

    protected void resetSubject(GameObject gameObject) {
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
            path = SpatialPathUtils.findPath(graph, waypoint, endpoint);
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
        events.moveUpdate(gameObject, waypoint);
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
