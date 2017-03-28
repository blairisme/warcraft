package com.evilbird.engine.common.ai;

import com.badlogic.gdx.ai.pfa.Connection;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ManhattanConnection<T extends SpatialNode> implements Connection<T>
{
    private float cost;
    private T fromNode;
    private T toNode;

    public ManhattanConnection(T fromNode, T toNode)
    {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.cost = -1;
    }

    @Override
    public float getCost()
    {
        if (cost == -1){
            ManhattanHeuristic heuristic = new ManhattanHeuristic();
            cost = heuristic.estimate(fromNode, toNode);
        }
        return cost;
    }

    @Override
    public T getFromNode()
    {
        return fromNode;
    }

    @Override
    public T getToNode()
    {
        return toNode;
    }
}
