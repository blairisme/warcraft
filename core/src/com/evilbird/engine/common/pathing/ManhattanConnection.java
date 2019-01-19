/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.pathing;

import com.badlogic.gdx.ai.pfa.Connection;
import com.evilbird.engine.item.SpatialItemNode;

public class ManhattanConnection<T extends SpatialItemNode> implements Connection<T>
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
