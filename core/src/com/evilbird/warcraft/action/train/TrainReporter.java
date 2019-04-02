/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.warcraft.action.common.production.CreateObserver;
import com.evilbird.warcraft.action.common.resource.ResourceTransferEvent;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;

import javax.inject.Inject;

/**
 * Instances of this class observe training events and report them to the
 * {@link EventQueue}.
 *
 * @author Blair Butterworth
 */
public class TrainReporter implements TrainObserver, CreateObserver
{
    private EventQueue events;

    @Inject
    public TrainReporter(EventQueue events) {
        this.events = events;
    }

    @Override
    public void onTransfer(ResourceContainer recipient, ResourceIdentifier resource, float value) {
        events.add(new ResourceTransferEvent(recipient, resource, value));
    }
}
