/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.spatial;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.pathing.SpatialConnection;

/**
 * Instances of this class represent a connection between two
 * {@link ItemNode SpatialItemNodes} of a {@link ItemGraph}.
 *
 * @author Blair Butterworth
 */
public class ItemConnection implements SpatialConnection<ItemNode>
{
    protected float cost;
    protected ItemNode fromNode;
    protected ItemNode toNode;

    public ItemConnection(ItemNode fromNode, ItemNode toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.cost = calculateCost(fromNode, toNode);
    }

    private float calculateCost(ItemNode fromNode, ItemNode toNode) {
        GridPoint2 startIndex = fromNode.getSpatialReference();
        GridPoint2 endIndex = toNode.getSpatialReference();
        return Math.abs(endIndex.x - startIndex.x) + Math.abs(endIndex.y - startIndex.y);
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public ItemNode getFromNode() {
        return fromNode;
    }

    @Override
    public ItemNode getToNode() {
        return toNode;
    }
}