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
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.common.resource.ResourceType;

import static com.evilbird.warcraft.action.common.transfer.TransferEvents.notifyTransfer;

public class TransferOperations
{
    public static void setResources(ResourceContainer container, Iterable<ResourceQuantity> resources, Events events) {
        for (ResourceQuantity resource: resources) {
            setResources(container, resource, events);
        }
    }

    public static void setResources(ResourceContainer container, ResourceQuantity quantity, Events events) {
        ResourceType resource = quantity.getType();
        float delta = quantity.getValue();

        if (delta != 0) {
            float oldValue = container.getResource(resource);
            float newValue = oldValue + delta;

            container.setResource(resource, newValue);
            notifyTransfer(events, container, resource, oldValue, newValue);
        }
    }
}
