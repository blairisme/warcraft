/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.item.common.resource.ResourceSet;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerUpgrade;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.action.common.transfer.TransferOperations.setResources;
import static com.evilbird.warcraft.action.produce.ProduceEvents.notifyProductionCompleted;
import static com.evilbird.warcraft.action.produce.ProduceEvents.notifyProductionStarted;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.item.unit.UnitCosts.buildTime;
import static com.evilbird.warcraft.item.unit.UnitCosts.cost;

/**
 * Instances of this action sequence research an upgrade, decrementing the
 * players resources and adding delay before the upgrade is applied.
 *
 * @author Blair Butterworth
 */
public class ProduceUpgrade extends DelayedAction
{
    private transient Events events;

    /**
     * Constructs a new instance of this class given an {@link EventQueue}
     * used to report events when production begins and completes, as well as
     * for the transfer of funds involved in production.
     *
     * @param events  a {@code EventQueue} instance. This parameter
     *                  cannot be {@code null}.
     */
    @Inject
    public ProduceUpgrade(EventQueue events) {
        this.events = events;
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            return initialize();
        }
        if (super.act(time)) {
            return complete();
        }
        return update(time);
    }

    private boolean initialized() {
        Building building = (Building)getItem();
        return building.isProducing();
    }

    private boolean initialize() {
        Building building = (Building)getItem();
        building.setProductionProgress(0);

        Player player = getPlayer(building);
        PlayerUpgrade product = getProduct();

        ResourceSet cost = new ResourceSet(cost(product));
        setResources(player, cost.negate(), events);

        setDuration(buildTime(product));
        setProgress(building.getProductionProgress() * getDuration());

        notifyProductionStarted(events, building);
        return ActionIncomplete;
    }

    private boolean complete() {
        Building building = (Building)getItem();
        Player player = getPlayer(building);

        player.setUpgrade(getProduct(), true);
        notifyProductionCompleted(events, building);

        return ActionComplete;
    }

    private boolean update(float time) {
        Building building = (Building)getItem();
        building.setProductionProgress(getProgress());
        return ActionIncomplete;
    }

    private PlayerUpgrade getProduct() {
        ProduceUpgradeActions identifier = (ProduceUpgradeActions)getIdentifier();
        return identifier.getProduct();
    }
}