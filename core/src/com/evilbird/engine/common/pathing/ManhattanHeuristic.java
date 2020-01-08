/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.pathing;

import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.math.GridPoint2;

public class ManhattanHeuristic<T extends SpatialNode> implements Heuristic<T>
{
    @Override
    public float estimate(T node, T endNode) {
        GridPoint2 startIndex = node.getSpatialReference();
        GridPoint2 endIndex = node.getSpatialReference();
        return Math.abs(endIndex.x - startIndex.x) + Math.abs(endIndex.y - startIndex.y);
    }
}
