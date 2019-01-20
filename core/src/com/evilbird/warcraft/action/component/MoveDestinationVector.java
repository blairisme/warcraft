/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.component;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.SpatialGraph;
import com.evilbird.engine.item.SpatialItemNode;

/**
 * @author Blair Butterworth
 */
public class MoveDestinationVector implements MoveDestination
{
    private Vector2 destination;

    public MoveDestinationVector(Vector2 destination) {
        this.destination = destination;
    }

    @Override
    public SpatialItemNode getDestinationNode(SpatialGraph graph) {
        return graph.getNode(destination);
    }

    @Override
    public Vector2 getOrientationTarget() {
        return destination;
    }

    @Override
    public boolean isDestinationValid() {
        return true;
    }
}
