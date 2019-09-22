/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.transfer;

import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;

import javax.inject.Inject;

/**
 * Notifies the system event observers about resource transfers.
 *
 * Helper class for generating transfer events.
 *
 * @author Blair Butterworth
 */
public class TransferEvents
{
    private Events events;

    @Inject
    public TransferEvents(Events events) {
        this.events = events;
    }

    public void notifyTransfer(ResourceContainer subject, ResourceType resource, float value) {
        events.add(new TransferEvent(subject, resource, value));
    }
}
