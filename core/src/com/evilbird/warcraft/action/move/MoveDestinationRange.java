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
import com.evilbird.engine.common.lang.Alignment;
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
class MoveDestinationRange implements MoveDestination
{
    private Item target;
    private int range;
    private ItemNode targetNode;

    public MoveDestinationRange(Item target, int range) {
        this.target = target;
        this.range = range;
    }

    @Override
    public Vector2 getDestination() {
        return target.getPosition();
    }

    @Override
    public ItemNode getDestinationNode(ItemGraph graph, ItemNode node, ItemPathFilter traversable) {
        Collection<ItemNode> foo = graph.getNodes(target);
        targetNode = foo.iterator().next();

        Collection<ItemNode> nodes = graph.getAdjacentNodes(target.getPosition(), target.getSize(), range);
        nodes.removeIf(not(traversable));
        return nodes.isEmpty() ? null : SpatialUtils.getClosest(nodes, node);
    }

    @Override
    public boolean isDestinationValid(ItemGraph graph, ItemNode node) {
        return targetNode.hasOccupant(target);
    }

    @Override
    public boolean isDestinationReached(ItemGraph graph, ItemNode node) {
        Vector2 targetPosition = target.getPosition(Alignment.Center);
        Vector2 nodePosition = node.getWorldReference(Alignment.Center);
        float distance = targetPosition.dst(nodePosition);
        return distance < range;
    }
}
