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
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.item.common.production.ProductionCosts;
import com.evilbird.warcraft.item.common.production.ProductionTimes;
import com.evilbird.warcraft.item.common.resource.ResourceSet;
import com.evilbird.warcraft.item.common.upgrade.Upgrade;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;

/**
 * Instances of this action sequence research an upgrade, decrementing the
 * players resources and adding delay before the upgrade is applied.
 *
 * @author Blair Butterworth
 */
public class ProduceUpgrade extends BasicAction
{
    private transient GameTimer timer;
    private transient ProduceEvents events;
    private transient ResourceTransfer resources;
    private transient ProductionCosts productionCosts;
    private transient ProductionTimes productionTimes;

    @Inject
    public ProduceUpgrade(
        ProduceEvents events,
        ResourceTransfer resources,
        ProductionCosts productionCosts,
        ProductionTimes productionTimes)
    {
        this.events = events;
        this.resources = resources;
        this.productionCosts = productionCosts;
        this.productionTimes = productionTimes;
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
        Building building = (Building)getItem();
        return building.isProducing();
    }

    private boolean initialize() {
        Building building = (Building)getItem();
        building.setProductionProgress(0);

        Player player = getPlayer(building);
        Upgrade product = getProduct();

        ResourceSet cost = new ResourceSet(productionCosts.costOf(product));
        resources.setResources(player, cost.negate());

        events.notifyProductionStarted(building);
        return ActionIncomplete;
    }

    protected boolean loaded() {
        return timer != null;
    }

    protected boolean load() {
        Building building = (Building)getItem();
        Upgrade product = getProduct();
        timer = new GameTimer(productionTimes.buildTime(product));
        timer.advance(building.getProductionProgress() * timer.duration());
        return ActionIncomplete;
    }

    private boolean update() {
        Building building = (Building)getItem();
        building.setProductionProgress(timer.completion());
        return ActionIncomplete;
    }

    private boolean complete() {
        Building building = (Building)getItem();
        building.setProductionProgress(1);
        Player player = getPlayer(building);

        player.setUpgrade(getProduct(), true);
        events.notifyProductionCompleted(building);

        return ActionComplete;
    }

    private Upgrade getProduct() {
        ProduceUpgradeActions identifier = (ProduceUpgradeActions)getIdentifier();
        return identifier.getProduct();
    }
}