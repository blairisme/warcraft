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

import javax.inject.Inject;

import static com.evilbird.warcraft.action.common.transfer.TransferAction.deposit;
import static com.evilbird.warcraft.action.produce.ProduceAction.stopProducing;
import static com.evilbird.warcraft.action.produce.ProduceEvents.onProductionCancelled;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isProducing;
import static com.evilbird.warcraft.item.unit.UnitCosts.cost;

/**
 * Instances of this class stop the production of an upgrade, refunding a
 * proportion of the cost of producing it.
 *
 * @author Blair Butterworth
 */
public class ProduceUpgradeCancel extends ScenarioAction<ProduceUpgradeActions>
{
    private transient Events events;

    /**
     * Constructs a new instance of this class given an {@link EventQueue}
     * used to report the transfer of resources involved in the refund for the
     * partially complete production operation.
     *
     * @param events  a {@code EventQueue} instance. This parameter
     *                  cannot be {@code null}.
     */
    @Inject
    public ProduceUpgradeCancel(EventQueue events) {
        this.events = events;
    }

    @Override
    protected void steps(ProduceUpgradeActions action) {
        scenario(action);
        steps(action.getProduct());
    }

    private void steps(PlayerUpgrade upgrade) {
        given(isProducing());
        then(stopProducing());
        then(deposit(cost(upgrade), events));
        then(onProductionCancelled(events));
    }
}