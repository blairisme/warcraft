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
import com.evilbird.engine.common.lang.Positionable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.SpatialGraph;
import com.evilbird.engine.item.SpatialItemNode;

import java.util.Map;

public class MoveDestinationItem implements MoveDestination
{
    private Item destination;
    private SpatialGraph graph;

    public MoveDestinationItem(Item destination) {
        this.destination = destination;
        this.graph = destination.getRoot().getSpatialGraph();
    }

    @Override
    public SpatialItemNode getDestinationNode(SpatialGraph graph) {
        return graph.getAdjacentNode(destination.getPosition());
    }

    @Override
    public Vector2 getOrientationTarget() {
        return destination.getPosition();
    }

    @Override
    public boolean isDestinationValid() {
        Map<Item, SpatialItemNode> newOccupants = graph.getNewOccupants();
        return !newOccupants.containsKey(destination);
    }
}
