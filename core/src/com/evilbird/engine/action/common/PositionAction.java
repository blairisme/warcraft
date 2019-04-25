/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

/**
 * Instances of this {@link Action} position the subject of the action in the
 * first unoccupied position adjacent to the actions target.
 *
 * @author Blair Butterworth
 */
public class PositionAction extends BasicAction
{
    @Inject
    public PositionAction() {
    }

    public static PositionAction positionAdjacent() {
        return new PositionAction();
    }

    @Override
    public boolean act(float delta) {
        Item item = getItem();
        Item target = getTarget();

        ItemRoot root = target.getRoot();
        ItemGraph graph = root.getSpatialGraph();

        Collection<ItemNode> adjacent = graph.getAdjacentNodes(target.getPosition(), target.getSize());
        Optional<ItemNode> unoccupied = adjacent.stream().filter(node -> !node.hasOccupants()).findFirst();

        if (unoccupied.isPresent()) {
            ItemNode destination = unoccupied.get();
            item.setPosition(destination.getWorldReference());
        }
        return true;
    }
}
