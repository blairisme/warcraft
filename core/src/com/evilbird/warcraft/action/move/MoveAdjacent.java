/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.item.common.state.MovableObject;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

public class MoveAdjacent
{
    private MoveEvents events;

    @Inject
    public MoveAdjacent(MoveEvents events) {
        this.events = events;
    }

    public boolean reposition(MovableObject subject, Item target) {
        ItemRoot root = target.getRoot();
        ItemGraph graph = root.getSpatialGraph();

        ItemPathFilter capability = new ItemPathFilter();
        capability.addTraversableCapability(subject.getMovementCapability());

        Collection<ItemNode> adjacent = graph.getAdjacentNodes(target.getPosition(), target.getSize());
        Optional<ItemNode> unoccupied = adjacent.stream().filter(capability).findFirst();

        if (unoccupied.isPresent()) {
            graph.removeOccupants(subject);
            ItemNode destination = unoccupied.get();
            subject.setPosition(destination.getWorldReference());
            graph.addOccupants(subject);
            events.notifyMove(subject);
            return true;
        }
        return false;
    }
}
