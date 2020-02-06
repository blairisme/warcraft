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
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitProduction;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;

/**
 * A leaf task that selects the next building to construct, based on the order
 * and manifest contained in the tasks blackboard.
 *
 * @author Blair Butterworth
 */
public class ConstructNext extends LeafTask<ConstructData>
{
    @Inject
    public ConstructNext() {
    }

    @Override
    public Status execute() {
        ConstructData data = getObject();
        Player player = data.getPlayer();

        ConstructOrder order = data.getOrder();
        ConstructManifest manifest = data.getManifest();
        UnitType building = order.getNextBuilding(manifest);

        if (building != null) {
            UnitProduction production = UnitProduction.forProduct(building);

            if (player.hasResources(production.getCost())) {
                data.setBuilding(building);
                return Status.SUCCEEDED;
            }
        }
        return Status.FAILED;
    }

    @Override
    protected Task<ConstructData> copyTo(Task<ConstructData> task) {
        return task;
    }
}
