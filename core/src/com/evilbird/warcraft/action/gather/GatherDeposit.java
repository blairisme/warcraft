/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.action.common.exclusion.Exclusion;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.action.common.transfer.TransferOperations.setResources;
import static com.evilbird.warcraft.action.gather.GatherEvents.notifyDepositComplete;
import static com.evilbird.warcraft.action.gather.GatherEvents.notifyDepositStarted;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.IdleBasic;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;
import static com.evilbird.warcraft.item.unit.UnitAnimation.MoveBasic;

public class GatherDeposit extends DelayedAction
{
    private Events events;
    private ResourceQuantity quantity;

    @Inject
    public GatherDeposit(Events events) {
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
        return ActionIncomplete;
    }

    public void setDuration(float duration) {
        super.setDuration(duration);
    }

    public void setResource(ResourceQuantity quantity) {
        this.quantity = quantity;
    }

    private boolean initialized() {
        Gatherer gatherer = (Gatherer)getItem();
        return gatherer.isGathering();
    }

    private boolean initialize() {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setGathererProgress(0);
        Exclusion.disable(gatherer, events);

        setProgress(gatherer.getGathererProgress() * getDuration());
        Building depot = (Building)getTarget();

        notifyDepositStarted(events, gatherer, depot, quantity);
        return ActionIncomplete;
    }

    private boolean complete() {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setGathererProgress(1);
        gatherer.setAnimationAlias(IdleBasic, Idle);
        gatherer.setAnimationAlias(MoveBasic, Move);
        Exclusion.restore(gatherer);

        Building depot = (Building)getTarget();
        Player player = getPlayer(depot);

        setResources(player, quantity, events);
        setResources(gatherer, quantity.negate(), events);

        notifyDepositComplete(events, gatherer, depot, quantity);
        return ActionComplete;
    }

    private boolean update(float time) {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setGathererProgress(getProgress());
        return ActionIncomplete;
    }
}
