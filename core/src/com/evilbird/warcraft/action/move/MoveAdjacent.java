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

import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.object.common.capability.MovableObject;

import javax.inject.Inject;
import java.util.Collection;

public class MoveAdjacent
{
    private MoveEvents events;

    @Inject
    public MoveAdjacent(MoveEvents events) {
        this.events = events;
    }

    public boolean reposition(MovableObject subject, GameObject target) {
        GameObjectContainer root = target.getRoot();
        GameObjectGraph graph = root.getSpatialGraph();

        ItemPathFilter capability = new ItemPathFilter();
        capability.addTraversableCapability(subject.getMovementCapability());

        Collection<GameObjectNode> adjacent = graph.getAdjacentNodes(target.getPosition(), target.getSize());
        GameObjectNode unoccupied = CollectionUtils.findFirst(adjacent, capability);

        if (unoccupied != null) {
            graph.removeOccupants(subject);
            subject.setPosition(unoccupied.getWorldReference());
            graph.addOccupants(subject);
            events.move(subject);
            return true;
        }
        return false;
    }
}
