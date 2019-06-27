/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.action.common.scenario.ScenarioAction;
import com.evilbird.warcraft.item.data.player.PlayerUpgrade;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.common.transfer.TransferAction.purchase;
import static com.evilbird.warcraft.action.common.upgrade.UpgradeAction.applyUpgrade;
import static com.evilbird.warcraft.action.produce.ProduceAction.startProducing;
import static com.evilbird.warcraft.action.produce.ProduceEvents.onProductionCompleted;
import static com.evilbird.warcraft.action.produce.ProduceEvents.onProductionStarted;
import static com.evilbird.warcraft.action.produce.ProductionTimes.productionTime;
import static com.evilbird.warcraft.action.produce.ProductionValues.getUpgrade;
import static com.evilbird.warcraft.action.produce.ProductionValues.getUpgradeValue;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.unit.UnitCosts.cost;

/**
 * Instances of this action sequence research an upgrade, decrementing the
 * players resources and adding delay before the upgrade is applied.
 *
 * @author Blair Butterworth
 */
public class ProduceUpgrade extends ScenarioAction<ProduceActions>
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
    protected void steps(ProduceActions action) {
        scenario(action);
        steps(getUpgrade(action), getUpgradeValue(action));
    }

    private void steps(PlayerUpgrade upgrade, int value) {
        given(isAlive());
        then(purchase(cost(upgrade), events));
        then(onProductionStarted(events));
        then(startProducing(startTime(upgrade), productionTime(upgrade)));
        then(applyUpgrade(upgrade, value, events));
        then(onProductionCompleted(events));
    }

    private float startTime(PlayerUpgrade upgrade) {
        Building building = (Building)getItem();
        if (building.isProducing()) {
            return building.getProductionProgress() * productionTime(upgrade);
        }
        return 0;
    }
}