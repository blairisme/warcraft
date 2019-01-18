package com.evilbird.engine.common.ai;

import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.item.SpatialItemNode;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ManhattanHeuristic implements Heuristic<SpatialItemNode>
{
    @Override
    public float estimate(SpatialItemNode node, SpatialItemNode endNode) {
        GridPoint2 startIndex = node.getSpatialReference();
        GridPoint2 endIndex = node.getSpatialReference();
        return Math.abs(endIndex.x - startIndex.x) + Math.abs(endIndex.y - startIndex.y);
    }
}
