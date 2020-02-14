/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.gather;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectType;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;
import static com.evilbird.warcraft.action.gather.GatherLocations.closestResource;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.object.layer.LayerType.Tree;
import static com.evilbird.warcraft.object.unit.UnitType.GoldMine;
import static com.evilbird.warcraft.object.unit.UnitType.OilPlatform;
import static com.evilbird.warcraft.object.unit.UnitType.OilRig;

/**
 * A {@link LeafTask} implementation that selects the location from which to
 * start gathering.
 *
 * @author Blair Butterworth
 */
public class SelectLocation extends LeafTask<GatherData>
{
    @Inject
    public SelectLocation() {
    }

    @Override
    public Status execute() {
        GatherData data = getObject();
        GameObject location = getLocation(data);
        data.setLocation(location);
        return location != null ? SUCCEEDED : FAILED;
    }

    private GameObject getLocation(GatherData data) {
        Player player = data.getPlayer();
        ResourceType resource = data.getResource();
        GameObjectType type = getType(resource, player);
        MovableObject gatherer = (MovableObject)data.getGatherer();
        return closestResource(gatherer, gatherer, type);
    }

    private GameObjectType getType(ResourceType resource, Player player) {
        switch (resource) {
            case Gold: return GoldMine;
            case Oil: return player.getFaction() == Orc ? OilRig : OilPlatform;
            case Wood: return Tree;
            default: throw new UnsupportedOperationException();
        }
    }

    @Override
    protected Task<GatherData> copyTo(Task<GatherData> task) {
        return task;
    }
}
