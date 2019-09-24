/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.transfer;

import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.common.resource.ResourceType;

import javax.inject.Inject;

public class ResourceTransfer
{
    private TransferEvents events;

    @Inject
    public ResourceTransfer(TransferEvents events) {
        this.events = events;
    }

    public void transfer(ResourceContainer from, ResourceContainer to) {
        transfer(from, to, from.getResources());
    }

    public void transfer(ResourceContainer from, ResourceContainer to, Iterable<ResourceQuantity> quantities) {
        quantities.forEach(quantity -> transfer(from, to, quantity));
    }

    public void transfer(ResourceContainer from, ResourceContainer to, ResourceQuantity quantity) {
        setResources(from, quantity.negate());
        setResources(to, quantity);
    }

    public void setResources(ResourceContainer container, Iterable<ResourceQuantity> quantities) {
        quantities.forEach(quantity -> setResources(container, quantity));
    }

    public void setResources(ResourceContainer container, ResourceQuantity quantity) {
        ResourceType resource = quantity.getType();
        float delta = quantity.getValue();

        if (delta != 0) {
            float oldValue = container.getResource(resource);
            float newValue = oldValue + delta;

            container.setResource(resource, newValue);
            events.notifyTransfer(container, resource, newValue);
        }
    }
}
