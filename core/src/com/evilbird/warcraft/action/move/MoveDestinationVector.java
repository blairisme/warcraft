/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;

import java.util.Objects;

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
    public Vector2 getDestination() {
        return destination;
    }

    @Override
    public ItemNode getDestinationNode(ItemGraph graph, ItemNode node, ItemPathFilter filter) {
        return graph.getNode(destination);
    }

    @Override
    public boolean isDestinationValid(ItemGraph graph, ItemNode node) {
        return true;
    }

    @Override
    public boolean isDestinationReached(ItemGraph graph, ItemNode node) {
        return Objects.equals(node.getWorldReference(), destination);
    }
}
