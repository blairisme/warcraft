/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.data.resource.ResourceQuantity;
import com.evilbird.warcraft.object.common.production.ProductionCosts;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.warcraft.object.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.object.common.query.UnitOperations.moveAdjacent;

/**
 * Instances of this class stop the construction of a building.
 *
 * @author Blair Butterworth
 */
public class ConstructCancel extends DelegateAction
{
    private transient boolean cancelled;
    private transient ConstructEvents events;
    private transient ItemExclusion exclusion;
    private transient ResourceTransfer resources;
    private transient ProductionCosts costs;

    @Inject
    public ConstructCancel(
        ConstructEvents events,
        DeathAction death,
        ItemExclusion exclusion,
        ResourceTransfer resources,
        ProductionCosts costs)
    {
        super(death);
        this.costs = costs;
        this.events = events;
        this.cancelled = false;
        this.exclusion = exclusion;
        this.resources = resources;
    }

    @Override
    public boolean act(float delta) {
        if (! cancelled) {
            cancelled = true;
            cancel();
        }
        return super.act(delta);
    }

    @Override
    public void reset() {
        super.reset();
        cancelled = false;
    }

    private void cancel() {
        Building building = (Building) getSubject();
        Gatherer builder = (Gatherer)getTarget();

        configureBuilding(building);
        configureBuilder(builder, building);
        configurePlayer(building);

        events.notifyConstructCancelled(building);
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

    private Collection<ResourceQuantity> getBuildingCost() {
        return costs.costOf(getBuildingType());
    }

    private UnitType getBuildingType() {
        ConstructActions constructAction = (ConstructActions)getIdentifier();
        return constructAction.getProduct();
    }
}
