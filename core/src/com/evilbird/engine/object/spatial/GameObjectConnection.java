/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.spatial;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.pathing.SpatialConnection;

/**
 * Instances of this class represent a connection between two
 * {@link GameObjectNode GameObjectNodes} belonging to a
 * {@link GameObjectGraph}.
 *
 * @author Blair Butterworth
 */
public class GameObjectConnection implements SpatialConnection<GameObjectNode>
{
    protected float cost;
    protected GameObjectNode fromNode;
    protected GameObjectNode toNode;

    public GameObjectConnection(GameObjectNode fromNode, GameObjectNode toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.cost = calculateCost(fromNode, toNode);
    }

    private float calculateCost(GameObjectNode fromNode, GameObjectNode toNode) {
        GridPoint2 startIndex = fromNode.getSpatialReference();
        GridPoint2 endIndex = toNode.getSpatialReference();
        return Math.abs(endIndex.x - startIndex.x) + Math.abs(endIndex.y - startIndex.y);
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public GameObjectNode getFromNode() {
        return fromNode;
    }

    @Override
    public GameObjectNode getToNode() {
        return toNode;
    }
}