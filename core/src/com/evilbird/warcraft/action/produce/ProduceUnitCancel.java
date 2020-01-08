/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.object.common.production.ProductionCosts;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.warcraft.object.common.query.UnitOperations.getPlayer;

/**
 * Instances of this class stop the production of a Unit, refunding a
 * proportion of the cost of producing it.
 *
 * @author Blair Butterworth
 */
public class ProduceUnitCancel extends BasicAction
{
    private transient ProduceEvents events;
    private transient ResourceTransfer resources;
    private transient ProductionCosts costs;

    @Inject
    public ProduceUnitCancel(
        ProduceEvents events,
        ResourceTransfer resources,
        ProductionCosts costs)
    {
        this.events = events;
        this.resources = resources;
        this.costs = costs;
    }

    @Override
    public boolean act(float delta) {
        Building building = (Building) getSubject();
        building.setProductionProgress(1);

        Player player = getPlayer(building);
        resources.setResources(player, costs.costOf(getProduct()));

        events.notifyProductionCancelled(building);
        return ActionComplete;
    }

    private UnitType getProduct() {
        ProduceUnitActions identifier = (ProduceUnitActions)getIdentifier();
        return identifier.getProduct();
    }
}