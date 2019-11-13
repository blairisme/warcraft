/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.action.selection.SelectEvents;
import com.evilbird.warcraft.object.common.production.ProductionCosts;
import com.evilbird.warcraft.object.common.production.ProductionTimes;
import com.evilbird.warcraft.object.common.resource.ResourceSet;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.object.common.query.UnitOperations.getPlayer;

/**
 * Instances of this action sequence upgrade an existing building, decrementing
 * the players resources and adding delay before the building improvement can
 * be used.
 *
 * @author Blair Butterworth
 */
public class ConstructUpgrade extends BasicAction
{
    private transient GameTimer timer;
    private transient GameObjectFactory factory;
    private transient ResourceTransfer resources;
    private transient SelectEvents selectEvents;
    private transient ConstructEvents constructEvents;
    private transient ProductionCosts productionCosts;
    private transient ProductionTimes productionTimes;

    @Inject
    public ConstructUpgrade(
        GameObjectFactory factory,
        ConstructEvents constructEvents,
        SelectEvents selectEvents,
        ResourceTransfer resources,
        ProductionCosts productionCosts,
        ProductionTimes productionTimes)
    {
        this.factory = factory;
        this.resources = resources;
        this.selectEvents = selectEvents;
        this.constructEvents = constructEvents;
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
        Building building = (Building) getSubject();
        return building.isConstructing();
    }

    private boolean initialize() {
        Building building = (Building) getSubject();
        building.setConstructionProgress(0);
        building.setAnimation(UnitAnimation.BuildingUpgrade);

        Player player = getPlayer(building);
        UnitType product = getProduct();

        ResourceSet cost = new ResourceSet(productionCosts.costOf(product));
        resources.setResources(player, cost.negate());

        constructEvents.notifyConstructUpgrade(building);
        return ActionIncomplete;
    }

    protected boolean loaded() {
        return timer != null;
    }

    protected boolean load() {
        Building building = (Building) getSubject();
        UnitType product = getProduct();
        timer = new GameTimer(productionTimes.buildTime(product));
        timer.advance(building.getConstructionProgress() * timer.duration());
        return ActionIncomplete;
    }

    private boolean update() {
        Building building = (Building) getSubject();
        building.setConstructionProgress(timer.completion());
        return ActionIncomplete;
    }

    private boolean complete() {
        Building building = (Building) getSubject();
        Building improvement = (Building)factory.get(getProduct());

        Player player = getPlayer(building);
        player.removeObject(building);
        player.addObject(improvement);
        player.setUpgrades(building.getUpgrades(), true);

        improvement.setPosition(building.getPosition());
        improvement.clearUpgrades();

        if (building.getSelected()) {
            building.setSelected(false);
            improvement.setSelected(true);

            selectEvents.notifySelected(building, false);
            selectEvents.notifySelected(improvement, true);
        }
        constructEvents.notifyConstructComplete(improvement);
        return ActionComplete;
    }

    private UnitType getProduct() {
        ConstructActions identifier = (ConstructActions)getIdentifier();
        return identifier.getProduct();
    }
}