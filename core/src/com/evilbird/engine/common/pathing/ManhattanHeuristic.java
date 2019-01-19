/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.pathing;

import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.item.SpatialItemNode;

public class ManhattanHeuristic implements Heuristic<SpatialItemNode>
{
    @Override
    public float estimate(SpatialItemNode node, SpatialItemNode endNode) {
        GridPoint2 startIndex = node.getSpatialReference();
        GridPoint2 endIndex = node.getSpatialReference();
        return Math.abs(endIndex.x - startIndex.x) + Math.abs(endIndex.y - startIndex.y);
    }
}
