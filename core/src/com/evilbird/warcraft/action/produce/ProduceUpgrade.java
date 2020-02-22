/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.framework.AbstractAction;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.data.resource.ResourceSet;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.action.common.production.ProductionOperations.getProductionCost;
import static com.evilbird.warcraft.action.common.production.ProductionOperations.getProductionTime;
import static com.evilbird.warcraft.object.common.query.UnitOperations.getPlayer;

/**
 * Instances of this action sequence research an upgrade, decrementing the
 * players resources and adding delay before the upgrade is applied.
 *
 * @author Blair Butterworth
 */
public class ProduceUpgrade extends AbstractAction
{
    protected transient GameTimer timer;
    protected transient ProduceEvents events;
    protected transient ResourceTransfer resources;
    protected transient WarcraftPreferences preferences;

    @Inject
    public ProduceUpgrade(
        ProduceEvents events,
        ResourceTransfer resources,
        WarcraftPreferences preferences)
    {
        this.events = events;
        this.resources = resources;
        this.preferences = preferences;
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            return initialize();
        }
        if (! loaded()) {
            return load();
        }
        if (timer.advance(time)) {
            return complete();
        }
        return update();
    }

    @Override
    public void reset() {
        super.reset();
        timer = null;
    }

    @Override
    public void restart() {
        super.restart();
        timer = null;
    }

    private boolean initialized() {
        Building building = (Building) getSubject();
        return building.isProducing();
    }

    protected boolean initialize() {
        Building building = (Building) getSubject();
        building.setProductionProgress(0);

        Player player = getPlayer(building);
        Upgrade product = getProduct();

        ResourceSet cost = getProductionCost(product, preferences);
        resources.setResources(player, cost.negate());

        events.notifyProductionStarted(building, product);
        return ActionIncomplete;
    }

    private boolean loaded() {
        return timer != null;
    }

    protected boolean load() {
        Building building = (Building) getSubject();
        Upgrade product = getProduct();
        timer = new GameTimer(getProductionTime(product, preferences));
        timer.advance(building.getProductionProgress() * timer.duration());
        return ActionIncomplete;
    }

    protected boolean update() {
        Building building = (Building) getSubject();
        building.setProductionProgress(timer.completion());
        return ActionIncomplete;
    }

    protected boolean complete() {
        Building building = (Building) getSubject();
        building.setProductionProgress(1);
        Player player = getPlayer(building);

        player.setUpgrade(getProduct());
        events.notifyProductionCompleted(building, getProduct());

        return ActionComplete;
    }

    protected Upgrade getProduct() {
        ProduceUpgradeActions identifier = (ProduceUpgradeActions)getIdentifier();
        return identifier.getProduct();
    }
}