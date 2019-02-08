/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.event.Events;
import com.evilbird.warcraft.action.common.resource.ResourceTransferEvent;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;

import javax.inject.Inject;

public class TrainReporter implements TrainObserver
{
    private Events events;

    @Inject
    public TrainReporter(Events events) {
        this.events = events;
    }

    @Override
    public void onTransfer(ResourceContainer recipient, ResourceIdentifier resource, float value) {
        events.add(new ResourceTransferEvent(recipient, resource, value));
    }
}
