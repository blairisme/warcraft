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
 * @author Blair Butterworth
 */
public class SelectBuilding extends LeafTask<ConstructData>
{
    @Inject
    public SelectBuilding() {
    }

    @Override
    public Status execute() {
        ConstructData data = getObject();
        Player player = data.getPlayer();

        UnitType building = UnitType.Encampment; //TODO
        UnitProduction production = UnitProduction.forProduct(building);

        if (player.hasResources(production.getCost())) {
            data.setBuilding(building);
            return Status.SUCCEEDED;
        }
        return Status.FAILED;
    }

    @Override
    protected Task<ConstructData> copyTo(Task<ConstructData> task) {
        throw new UnsupportedOperationException();
    }
}
