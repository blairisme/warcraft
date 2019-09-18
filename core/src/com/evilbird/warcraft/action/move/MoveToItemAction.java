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
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.item.common.movement.Movable;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.engine.common.collection.CollectionUtils.filter;
import static com.evilbird.engine.common.pathing.SpatialUtils.getClosest;

/**
 * Instances of this {@link Action action} move an {@link Item} from its
 * current location to a given destination, specified as an Item. The moving
 * item will be animated with a movement animation, as well choose a path that
 * avoids obstacles.
 *
 * @author Blair Butterworth
 */
public class MoveToItemAction extends MoveAction
{
    private ItemNode endNode;
    private ItemNode targetNode;
    private ItemPathFilter filter;

    @Inject
    public MoveToItemAction(Events events) {
        super(events);
        setIdentifier(MoveActions.MoveToItem);
    }

    @Override
    public Vector2 getDestination() {
        Item target = getTarget();
        return target.getPosition();
    }

    @Override
    public ItemNode getEndNode(ItemNode node) {
        if (endNode == null) {
            Item target = getTarget();
            Collection<ItemNode> adjacentNodes = graph.getAdjacentNodes(target);
            Collection<ItemNode> traversableNodes = filter(adjacentNodes, getPathFilter());
            endNode = !traversableNodes.isEmpty() ? getClosest(traversableNodes, node) : null;
        }
        return endNode;
    }

    @Override
    public boolean destinationValid() {
        Item target = getTarget();
        if (targetNode == null) {
            Collection<ItemNode> nodes = graph.getNodes(target);
            targetNode = nodes.iterator().next();
        }
        return targetNode.hasOccupant(target);
    }

    @Override
    public boolean destinationReached(ItemNode node) {
        Item target = getTarget();
        Collection<Item> occupants = node.getOccupants();
        return occupants.contains(target);
    }

    @Override
    public ItemPathFilter getPathFilter() {
        if (filter == null) {
            Movable item = (Movable)getItem();
            filter = new ItemPathFilter();
            filter.addTraversableItem(item);
            filter.addTraversableItem(getTarget());
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

    public static MoveToItemAction move(Events events) {
        return moveToItem(events);
    }

    public static MoveToItemAction moveToItem(Events events) {
        return new MoveToItemAction(events);
    }
}
