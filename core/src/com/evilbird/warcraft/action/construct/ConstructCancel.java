/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.action.death.RemoveEvents;
import com.evilbird.warcraft.action.selection.SelectEvents;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.data.resource.ResourceSet;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.common.production.ProductionOperations.getProductionCost;
import static com.evilbird.warcraft.object.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.object.common.query.UnitOperations.moveAdjacent;

/**
 * Instances of this class stop the construction of a building.
 *
 * @author Blair Butterworth
 */
public class ConstructCancel extends DeathAction
{
    private transient ConstructEvents constructEvents;
    private transient ItemExclusion exclusion;
    private transient ResourceTransfer resources;
    private transient WarcraftPreferences preferences;

    @Inject
    public ConstructCancel(
        ConstructEvents constructEvents,
        SelectEvents selectEvents,
        RemoveEvents removeEvents,
        GameObjectFactory objectFactory,
        ItemExclusion exclusion,
        ResourceTransfer resources,
        WarcraftPreferences preferences)
    {
        super(selectEvents, removeEvents, objectFactory);
        this.constructEvents = constructEvents;
        this.exclusion = exclusion;
        this.resources = resources;
        this.preferences = preferences;
    }

    @Override
    protected boolean initialize() {
        Building building = (Building) getSubject();
        Gatherer builder = (Gatherer)getTarget();

        configureBuilding(building);
        configureBuilder(builder, building);
        configurePlayer(building);

        constructEvents.notifyConstructCancelled(building, builder);
        return super.initialize();
    }

    private void configureBuilding(Building building) {
        building.setConstructor(null);
        building.setConstructionProgress(1);
        building.setHealth(0);
    }

    private void configureBuilder(Gatherer builder, Building building) {
        builder.setConstruction(null);
        builder.removeActions();

        if (!builder.getVisible()) {
            exclusion.restore(builder);
            moveAdjacent(builder, building);
        }
    }

    private void configurePlayer(Building building) {
        Player player = getPlayer(building);
        resources.setResources(player, getBuildingCost());
    }

    private ResourceSet getBuildingCost() {
        return getProductionCost(getBuildingType(), preferences);
    }

    private UnitType getBuildingType() {
        ConstructActions constructAction = (ConstructActions)getIdentifier();
        return constructAction.getProduct();
    }
}
