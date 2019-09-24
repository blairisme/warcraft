/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.item.common.production.ProductionCosts;
import com.evilbird.warcraft.item.common.upgrade.Upgrade;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;

/**
 * Instances of this class stop the production of an upgrade, refunding a
 * proportion of the cost of producing it.
 *
 * @author Blair Butterworth
 */
public class ProduceUpgradeCancel extends BasicAction
{
    private transient ProduceEvents events;
    private transient ResourceTransfer resources;
    private transient ProductionCosts costs;

    @Inject
    public ProduceUpgradeCancel(
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
        Building building = (Building)getItem();
        building.setProductionProgress(1);

        Player player = getPlayer(building);
        resources.setResources(player, costs.costOf(getProduct()));

        events.notifyProductionCancelled(building);
        return ActionComplete;
    }

    private Upgrade getProduct() {
        ProduceUpgradeActions identifier = (ProduceUpgradeActions)getIdentifier();
        return identifier.getProduct();
    }
}