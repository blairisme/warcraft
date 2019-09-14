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
import com.evilbird.engine.common.pathing.SpatialUtils;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;

import java.util.Collection;

import static com.evilbird.engine.common.function.Predicates.not;

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
    public Vector2 getDestination() {
        return target.getPosition();
    }

    @Override
    public ItemNode getDestinationNode(ItemGraph graph, ItemNode node, ItemPathFilter traversable) {
        Collection<ItemNode> nodes = graph.getNodes(target);
        targetNode = nodes.iterator().next();

        Collection<ItemNode> adjacentNodes = graph.getAdjacentNodes(target);
        adjacentNodes.removeIf(not(traversable));

        return !adjacentNodes.isEmpty() ? SpatialUtils.getClosest(adjacentNodes, node) : null;
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
