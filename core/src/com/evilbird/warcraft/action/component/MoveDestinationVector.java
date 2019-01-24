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
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.SpatialGraph;
import com.evilbird.engine.item.SpatialItemNode;

/**
 * Instances of this {@link MoveDestination} represent a destination
 * {@link Vector2}. The moving item will be instructed to the exact location of
 * the given vector.
 *
 * @author Blair Butterworth
 */
class MoveDestinationVector implements MoveDestination
{
    private Vector2 destination;

    public MoveDestinationVector(Vector2 destination) {
        this.destination = destination;
    }

    @Override
    public SpatialItemNode getDestinationNode(SpatialGraph graph, SpatialItemNode node) {
        return graph.getNode(destination);
    }

    @Override
    public boolean isDestinationValid(SpatialGraph graph) {
        return true;
    }

    @Override
    public boolean isDestinationReached(SpatialItemNode node) {
        return Objects.equals(node.getWorldReference(), destination);
    }

    @Override
    public Vector2 getOrientationTarget() {
        return destination;
    }
}
