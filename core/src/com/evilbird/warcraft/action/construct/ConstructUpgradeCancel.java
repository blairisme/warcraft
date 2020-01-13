/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.warcraft.action.common.production.ProductionOperations.getProductionCost;
import static com.evilbird.warcraft.object.common.query.UnitOperations.getPlayer;

/**
 * Instances of this class stop the construction of a building upgrade,
 * refunding the cost of producing it.
 *
 * @author Blair Butterworth
 */
public class ConstructUpgradeCancel extends BasicAction
{
    private transient ConstructEvents events;
    private transient ResourceTransfer resources;
    private transient WarcraftPreferences preferences;

    @Inject
    public ConstructUpgradeCancel(
        ConstructEvents events,
        ResourceTransfer resources,
        WarcraftPreferences preferences)
    {
        this.events = events;
        this.resources = resources;
        this.preferences = preferences;
    }

    @Override
    public boolean act(float delta) {
        Building building = (Building) getSubject();
        building.setAnimation(UnitAnimation.Idle);
        building.setProductionProgress(1);

        Player player = getPlayer(building);
        resources.setResources(player, getProductionCost(getProduct(), preferences));

        events.notifyConstructCancelled(building);
        return ActionComplete;
    }

    private UnitType getProduct() {
        ConstructActions identifier = (ConstructActions)getIdentifier();
        return identifier.getProduct();
    }
}