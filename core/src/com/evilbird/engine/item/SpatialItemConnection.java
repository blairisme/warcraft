package com.evilbird.engine.item;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.GridPoint2;

/**
 * Instances of this class represent a connection between two
 * {@link SpatialItemNode SpatialItemNodes} of a {@link SpatialGraph}.
 */
public class SpatialItemConnection implements Connection<SpatialItemNode>
{
    protected float cost;
    protected SpatialItemNode fromNode;
    protected SpatialItemNode toNode;

    public SpatialItemConnection (SpatialItemNode fromNode, SpatialItemNode toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.cost = calculateCost(fromNode, toNode);
    }

    private float calculateCost(SpatialItemNode fromNode, SpatialItemNode toNode)
    {
        GridPoint2 startIndex = fromNode.getSpatialReference();
        GridPoint2 endIndex = toNode.getSpatialReference();
        return Math.abs(endIndex.x - startIndex.x) + Math.abs(endIndex.y - startIndex.y);
    }

    @Override
    public float getCost () {
        return cost;
    }

    @Override
    public SpatialItemNode getFromNode () {
        return fromNode;
    }

    @Override
    public SpatialItemNode getToNode () {
        return toNode;
    }
}