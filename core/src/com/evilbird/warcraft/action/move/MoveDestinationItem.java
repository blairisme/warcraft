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
import com.evilbird.engine.item.ItemGraph;
import com.evilbird.engine.item.ItemNode;

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
    private Item destination;

    public MoveDestinationItem(Item destination) {
        this.destination = destination;
    }

    @Override
    public ItemNode getDestinationNode(ItemGraph graph, ItemNode node) {
        Collection<ItemNode> nodes = graph.getNodes(destination.getPosition(), destination.getSize());
        return SpatialUtils.getClosest(nodes, node);
    }

    @Override
    public boolean isDestinationValid(ItemGraph graph, ItemNode node) {
        return node.hasOccupant(destination);
    }

    @Override
    public boolean isDestinationReached(ItemGraph graph, ItemNode node) {
        Collection<Item> occupants = node.getOccupants();
        return occupants.contains(destination);
    }
}
