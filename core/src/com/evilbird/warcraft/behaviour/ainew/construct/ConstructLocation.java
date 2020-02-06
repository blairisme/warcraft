/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.construct;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.action.common.spatial.ItemPathFilter;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.layer.terrain.Terrain;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;
import java.util.Collection;

/**
 * A leaf task that selects an unoccupied location in which to build the
 * building contained in the tasks blackboard.
 *
 * @author Blair Butterworth
 */
public class ConstructLocation extends LeafTask<ConstructData>
{
    @Inject
    public ConstructLocation() {
    }

    @Override
    public Status execute() {
        ConstructData data = getObject();
        Player player = data.getPlayer();
        GameObject commandCentre = player.find(UnitOperations::isCommandCentre);

        if (commandCentre != null) {
            Gatherer builder = (Gatherer)data.getBuilder();
            ItemPathFilter capability = new ItemPathFilter();
            capability.addTraversableCapability(builder.getMovementCapability());

            GameObjectContainer container = player.getRoot();
            GameObjectGraph graph = container.getSpatialGraph();

            Collection<GameObjectNode> adjacent = graph.getAdjacentNodes(commandCentre, 6*32);
            GameObjectNode unoccupied = CollectionUtils.findFirst(adjacent, capability);


            if (unoccupied != null) {
                data.setLocation(unoccupied.getWorldReference());
                return Status.SUCCEEDED;
            }
        }
        return Status.FAILED;
    }

    private GameObject getTerrain(GameObjectNode node) {
        if (node != null) {
            for (GameObject occupant : node.getOccupants()) {
                if (occupant instanceof Terrain) {

                }
            }
        }
        return null;
    }

    @Override
    protected Task<ConstructData> copyTo(Task<ConstructData> task) {
        throw new UnsupportedOperationException();
    }
}
