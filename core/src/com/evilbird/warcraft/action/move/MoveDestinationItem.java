/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.common.pathing.SpatialUtils;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;

import java.util.Collection;

/**
 * Instances of this {@link MoveDestination} represent a destination
 * {@link Item}. The moving item will be instructed to move next to, but not on
 * top of the destination.
 *
 * @author Blair Butterworth
 */
class MoveDestinationItem implements MoveDestination
{
    private Item target;
    private ItemNode targetNode;

    public MoveDestinationItem(Item target) {
        this.target = target;
    }

    @Override
    public ItemNode getDestinationNode(ItemGraph graph, ItemNode node) {
        Collection<ItemNode> nodes = graph.getNodes(target);
        targetNode = nodes.iterator().next();

        Collection<ItemNode> adjacentNodes = graph.getAdjacentNodes(target);
        return SpatialUtils.getClosest(adjacentNodes, node);
    }

    @Override
    public boolean isDestinationValid(ItemGraph graph, ItemNode node) {
        return targetNode.hasOccupant(target);
    }

    @Override
    public boolean isDestinationReached(ItemGraph graph, ItemNode node) {
        Collection<Item> occupants = node.getOccupants();
        return occupants.contains(target);
    }
}
