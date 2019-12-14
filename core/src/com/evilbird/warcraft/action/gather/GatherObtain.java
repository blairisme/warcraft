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
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.data.resource.ResourceContainer;
import com.evilbird.warcraft.data.resource.ResourceQuantity;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;

/**
 * An {@link Action} that obtains resources from a resource.
 *
 * @author Blair Butterworth
 */
class GatherObtain extends BasicAction
{
    protected transient GameTimer timer;
    protected transient DeathAction death;
    protected transient GatherEvents events;
    protected transient ResourceType resource;
    protected transient ResourceTransfer resources;

    public GatherObtain(GatherEvents events, DeathAction death, ResourceTransfer resources) {
        this.events = events;
        this.death = death;
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
        return update(time);
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

    protected boolean initialized() {
        Gatherer gatherer = (Gatherer) getSubject();
        return gatherer.isGathering() && !gatherer.hasOtherResource(resource);
    }

    protected boolean initialize() {
        Gatherer gatherer = (Gatherer) getSubject();
        gatherer.clearResources();
        gatherer.setGathererProgress(0);

        ResourceQuantity quantity = new ResourceQuantity(resource, getGatherCapacity(gatherer));
        events.notifyObtainStarted(gatherer, getTarget(), quantity);

        return ActionIncomplete;
    }

    protected boolean loaded() {
        return timer != null;
    }

    protected boolean load() {
        Gatherer gatherer = (Gatherer) getSubject();
        timer = new GameTimer(getGatherSpeed(gatherer));
        timer.advance(gatherer.getGathererProgress() * timer.duration());
        return ActionIncomplete;
    }

    protected boolean update(float time) {
        Gatherer gatherer = (Gatherer) getSubject();
        gatherer.setGathererProgress(timer.completion());
        return ActionIncomplete;
    }

    protected boolean complete() {
        Gatherer gatherer = (Gatherer) getSubject();
        gatherer.setGathererProgress(1);

        ResourceQuantity quantity = new ResourceQuantity(resource, getGatherCapacity(gatherer));
        ResourceContainer container = (ResourceContainer)getTarget();
        resources.transfer(container, gatherer, quantity);
        resourceEmpty(container);

        events.notifyObtainComplete(gatherer, container, quantity);
        return ActionComplete;
    }

    protected void resourceEmpty(ResourceContainer container) {
        if (container instanceof PerishableObject && container.getResource(resource) == 0) {
            death.setSubject(container);
            container.addAction(death);
        }
    }

    private float getGatherCapacity(Gatherer gatherer) {
        switch (resource) {
            case Gold: return gatherer.getGoldCapacity();
            case Oil: return gatherer.getOilCapacity();
            case Wood: return gatherer.getWoodCapacity();
            default: throw new UnsupportedOperationException();
        }
    }

    private float getGatherSpeed(Gatherer gatherer) {
        switch (resource) {
            case Gold: return gatherer.getGoldGatherSpeed();
            case Oil: return gatherer.getOilGatherSpeed();
            case Wood: return gatherer.getWoodGatherSpeed();
            default: throw new UnsupportedOperationException();
        }
    }
}