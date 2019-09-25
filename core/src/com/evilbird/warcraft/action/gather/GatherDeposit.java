/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.IdleBasic;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;
import static com.evilbird.warcraft.item.unit.UnitAnimation.MoveBasic;

/**
 * An {@link Action} that stores resources in a repository.
 *
 * @author Blair Butterworth
 */
public class GatherDeposit extends BasicAction
{
    private static final float DEPOSIT_DURATION = 5;

    private transient GameTimer timer;
    private transient GatherEvents events;
    private transient ResourceType resource;
    private transient ItemExclusion exclusion;
    private transient ResourceTransfer resources;

    @Inject
    public GatherDeposit(GatherEvents events, ItemExclusion exclusion, ResourceTransfer resources) {
        this.events = events;
        this.exclusion = exclusion;
        this.resources = resources;
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

    public void setResource(ResourceType resource) {
        this.resource = resource;
    }

    private boolean initialized() {
        Gatherer gatherer = (Gatherer)getItem();
        return gatherer.isGathering();
    }

    private boolean initialize() {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setGathererProgress(0);
        exclusion.disable(gatherer);

        Building depot = (Building)getTarget();
        ResourceQuantity quantity = new ResourceQuantity(resource, getGatherCapacity(gatherer));

        events.notifyDepositStarted(gatherer, depot, quantity);
        return ActionIncomplete;
    }

    protected boolean loaded() {
        return timer != null;
    }

    protected boolean load() {
        Gatherer gatherer = (Gatherer)getItem();
        timer = new GameTimer(DEPOSIT_DURATION);
        timer.advance(gatherer.getGathererProgress() * timer.duration());
        return ActionIncomplete;
    }

    private boolean update() {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setGathererProgress(timer.completion());
        return ActionIncomplete;
    }

    private boolean complete() {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setGathererProgress(1);
        gatherer.setAnimationAlias(IdleBasic, Idle);
        gatherer.setAnimationAlias(MoveBasic, Move);
        exclusion.restore(gatherer);

        Building depot = (Building)getTarget();
        Player player = getPlayer(depot);
        ResourceQuantity quantity = new ResourceQuantity(resource, getGatherCapacity(gatherer));
        resources.transfer(gatherer, player, quantity);

        events.notifyDepositComplete(gatherer, depot, quantity);
        return ActionComplete;
    }

    private float getGatherCapacity(Gatherer gatherer) {
        switch (resource) {
            case Gold: return gatherer.getGoldCapacity();
            case Oil: return gatherer.getOilCapacity();
            case Wood: return gatherer.getWoodCapacity();
            default: throw new UnsupportedOperationException();
        }
    }
}
