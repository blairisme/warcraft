/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.pathing.SpatialUtils;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.engine.common.collection.CollectionUtils.filter;

/**
 * Instances of this {@link Action action} move an {@link GameObject} from its
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
    public MoveWithinRangeAction(Events events) {
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
            GameObject target = getTarget();
            Combatant combatant = (Combatant) getSubject();
            int range = combatant.getAttackRange();
            Collection<GameObjectNode> adjacentNodes = graph.getAdjacentNodes(target.getPosition(), target.getSize(), range);
            Collection<GameObjectNode> traversableNodes = filter(adjacentNodes, getPathFilter());
            endNode = !traversableNodes.isEmpty() ? SpatialUtils.getClosest(traversableNodes, node) : null;
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
        Combatant combatant = (Combatant) getSubject();
        Vector2 targetPosition = target.getPosition(Alignment.Center);
        Vector2 nodePosition = node.getWorldReference(Alignment.Center);
        float distance = targetPosition.dst(nodePosition);
        return distance < combatant.getAttackRange();
    }

    @Override
    public ItemPathFilter getPathFilter() {
        if (filter == null) {
            MovableObject item = (MovableObject) getSubject();
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
}