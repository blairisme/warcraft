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
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.spatial.SpatialObject;
import com.evilbird.warcraft.action.common.spatial.SpatialPathFilter;
import com.evilbird.warcraft.object.common.capability.MovableObject;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.engine.common.collection.CollectionUtils.filter;
import static com.evilbird.engine.common.pathing.SpatialUtils.getClosest;

/**
 * Instances of this {@link Action action} move an {@link GameObject} from its
 * current location to a given destination, specified as an Item. The moving
 * item will be animated with a movement animation, as well choose a path that
 * avoids obstacles.
 *
 * @author Blair Butterworth
 */
public class MoveToItemAction extends MoveAction
{
    private GameObjectNode endNode;
    private GameObjectNode targetNode;
    private SpatialPathFilter filter;

    @Inject
    public MoveToItemAction(MoveEvents events) {
        super(events);
        setIdentifier(MoveActions.MoveToItem);
    }

    @Override
    public Vector2 getDestination() {
        GameObject target = getTarget();
        return target.getPosition();
    }

    @Override
    public GameObjectNode getEndNode(GameObjectNode node) {
        if (endNode == null) {
            GameObject target = getTarget();
            Collection<GameObjectNode> adjacentNodes = graph.getAdjacentNodes(target);
            Collection<GameObjectNode> traversableNodes = filter(adjacentNodes, getPathFilter());
            endNode = !traversableNodes.isEmpty() ? getClosest(traversableNodes, node) : null;
        }
        return endNode;
    }

    @Override
    public boolean destinationValid() {
        GameObject target = getTarget();
        if (target instanceof SpatialObject) {
            if (targetNode == null) {
                Collection<GameObjectNode> nodes = graph.getNodes(target);
                targetNode = nodes.iterator().next();
            }
            return targetNode.hasOccupant(target);
        }
        return true;
    }

    @Override
    public boolean destinationReached(GameObjectNode node) {
        GameObject target = getTarget();
        Collection<GameObject> occupants = node.getOccupants();
        return occupants.contains(target);
    }

    @Override
    public SpatialPathFilter getPathFilter() {
        if (filter == null) {
            MovableObject item = (MovableObject)getSubject();
            filter = new SpatialPathFilter();
            filter.addTraversableItem(item);
            filter.addTraversableCapability(item.getMovementCapability());
        }
        return filter;
    }

    @Override
    public void reset() {
        super.reset();
        filter = null;
        endNode = null;
        targetNode = null;
    }

    @Override
    public void restart() {
        super.restart();
        filter = null;
        endNode = null;
        targetNode = null;
    }
}
