/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.component;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGraph;
import com.evilbird.engine.item.ItemNode;
import com.evilbird.engine.common.pathing.SpatialUtils;

import java.util.Collection;
import java.util.Map;

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
    public Vector2 getOrientationTarget() {
        return destination.getPosition();
    }

    @Override
    public boolean isDestinationValid(ItemGraph graph) {
        Map<Item, ItemNode> newOccupants = graph.getNewOccupants();
        return !newOccupants.containsKey(destination);
    }

    @Override
    public boolean isDestinationReached(ItemNode node) {
        Collection<Item> occupants = node.getOccupants();
        return occupants.contains(destination);
    }
}
