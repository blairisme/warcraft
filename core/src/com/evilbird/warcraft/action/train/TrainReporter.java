/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.common.create.CreateObserver;
import com.evilbird.warcraft.action.common.resource.ResourceTransferEvent;
import com.evilbird.warcraft.action.common.resource.ResourceTransferObserver;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

/**
 * Instances of this class observe training events and report them to the
 * {@link EventQueue}.
 *
 * @author Blair Butterworth
 */
public class TrainReporter implements CreateObserver, TrainObserver, ResourceTransferObserver
{
    private EventQueue events;

    @Inject
    public TrainReporter(EventQueue events) {
        this.events = events;
    }

    @Override
    public void onCreate(Item item) {
        events.add(new CreateEvent(item));
    }

    @Override
    public void onTrainStarted(Building building) {
        events.add(new TrainEvent(building, TrainStatus.Started));
    }

    @Override
    public void onTrainCompleted(Building building) {
        events.add(new TrainEvent(building, TrainStatus.Complete));
    }

    @Override
    public void onTrainCancelled(Building building) {
        events.add(new TrainEvent(building, TrainStatus.Cancelled));
    }

    @Override
    public void onTransfer(ResourceContainer recipient, ResourceType resource, float oldValue, float newValue) {
        events.add(new ResourceTransferEvent(recipient, resource, oldValue, newValue));
    }
}
