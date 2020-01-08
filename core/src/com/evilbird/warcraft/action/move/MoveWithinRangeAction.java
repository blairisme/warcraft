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
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.pathing.SpatialUtils;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.engine.common.collection.CollectionUtils.filter;

/**
 * Instances of this {@link Action action} move a {@link GameObject} from its
 * current location to within attack range of a given target. The moving item
 * will be animated with a movement animation, as well choosing a path that
 * avoids obstacles.
 *
 * @author Blair Butterworth
 */
public class MoveWithinRangeAction extends MoveAction
{
    private GameObjectNode endNode;
    private GameObjectNode targetNode;
    private ItemPathFilter filter;

    @Inject
    public MoveWithinRangeAction(MoveEvents events) {
        super(events);
        setIdentifier(MoveActions.MoveWithRange);
    }

    @Override
    public Vector2 getDestination() {
        GameObject target = getTarget();
        return target.getPosition();
    }

    @Override
    public GameObjectNode getEndNode(GameObjectNode node) {
        if (endNode == null) {
            Collection<GameObjectNode> nodes = graph.getAdjacentNodes(getTarget(), getRange());
            Collection<GameObjectNode> traversable = filter(nodes, getPathFilter());
            endNode = !traversable.isEmpty() ? SpatialUtils.getClosest(traversable, node) : null;
        }
        return endNode;
    }

    @Override
    public boolean destinationValid() {
        GameObject target = getTarget();
        if (targetNode == null) {
            Collection<GameObjectNode> nodes = graph.getNodes(target);
            targetNode = nodes.iterator().next();
        }
        return targetNode.hasOccupant(target);
    }

    @Override
    public boolean destinationReached(GameObjectNode node) {
        GameObject target = getTarget();
        Vector2 targetPosition = target.getPosition(Alignment.Center);
        Vector2 nodePosition = node.getWorldReference(Alignment.Center);
        float distance = targetPosition.dst(nodePosition);
        return distance < getRange();
    }

    @Override
    public ItemPathFilter getPathFilter() {
        if (filter == null) {
            MovableObject item = (MovableObject)getSubject();
            filter = new ItemPathFilter();
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

    protected int getRange() {
        Combatant combatant = (Combatant)getSubject();
        return combatant.getAttackRange();
    }
}