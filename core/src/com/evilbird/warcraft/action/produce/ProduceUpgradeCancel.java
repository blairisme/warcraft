/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.data.resource.ResourceSet;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.common.production.ProductionOperations.getProductionCost;
import static com.evilbird.warcraft.object.common.query.UnitOperations.getPlayer;

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
    private transient WarcraftPreferences preferences;

    @Inject
    public ProduceUpgradeCancel(
        ProduceEvents events,
        ResourceTransfer resources,
        WarcraftPreferences preferences)
    {
        this.events = events;
        this.resources = resources;
        this.preferences = preferences;
    }

    @Override
    public ActionResult act(float delta) {
        Building building = (Building) getSubject();
        building.setProductionProgress(1);

        Upgrade upgrade = getProduct();
        ResourceSet cost = getProductionCost(upgrade, preferences);

        Player player = getPlayer(building);
        resources.setResources(player, cost);

        events.notifyProductionCancelled(building, upgrade);
        return ActionResult.Complete;
    }

    private Upgrade getProduct() {
        ProduceUpgradeActions identifier = (ProduceUpgradeActions)getIdentifier();
        return identifier.getProduct();
    }
}