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
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.action.common.transfer.TransferOperations.setResources;
import static com.evilbird.warcraft.action.gather.GatherEvents.notifyObtainComplete;
import static com.evilbird.warcraft.action.gather.GatherEvents.notifyObtainStarted;

/**
 * An {@link Action} that gathers resources from a resource.
 *
 * @author Blair Butterworth
 */
public class GatherObtain extends DelayedAction
{
    protected Events events;
    protected DeathAction death;
    protected ResourceQuantity quantity;

    @Inject
    public GatherObtain(Events events, DeathAction death) {
        this.events = events;
        this.death = death;
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

    public void setDuration(float duration) {
        super.setDuration(duration);
    }

    public void setResource(ResourceQuantity quantity) {
        this.quantity = quantity;
    }

    protected boolean initialized() {
        Gatherer gatherer = (Gatherer)getItem();
        return gatherer.isGathering() && !gatherer.hasOtherResource(quantity.getType());
    }

    protected boolean initialize() {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.clearResources();
        gatherer.setGathererProgress(0);
        setProgress(gatherer.getGathererProgress() * getDuration());

        ResourceContainer resource = (ResourceContainer)getTarget();
        notifyObtainStarted(events, gatherer, resource, quantity);

        return ActionIncomplete;
    }

    protected boolean complete() {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setGathererProgress(1);

        ResourceContainer resource = (ResourceContainer)getTarget();
        setResources(gatherer, quantity, events);
        setResources(resource, quantity.negate(), events);
        resourceEmpty(resource);

        notifyObtainComplete(events, gatherer, resource, quantity);
        return ActionComplete;
    }

    protected boolean update(float time) {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setGathererProgress(getProgress());
        return ActionIncomplete;
    }

    protected void resourceEmpty(ResourceContainer resource) {
        if (resource instanceof Destroyable && resource.getResource(quantity.getType()) == 0) {
            death.setItem(resource);
            resource.addAction(death);
        }
    }
}