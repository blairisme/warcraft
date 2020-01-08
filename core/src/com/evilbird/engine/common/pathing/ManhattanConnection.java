/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.pathing;

public class ManhattanConnection<T extends SpatialNode> implements SpatialConnection<T>
{
    private float cost;
    private T fromNode;
    private T toNode;

    public ManhattanConnection(T fromNode, T toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.cost = -1;
    }

    @Override
    public float getCost() {
        if (cost == -1) {
            ManhattanHeuristic<T> heuristic = new ManhattanHeuristic<>();
            cost = heuristic.estimate(fromNode, toNode);
        }
        return cost;
    }

    @Override
    public T getFromNode() {
        return fromNode;
    }

    @Override
    public T getToNode() {
        return toNode;
    }
}
