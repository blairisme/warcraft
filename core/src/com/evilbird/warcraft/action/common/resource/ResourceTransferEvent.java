/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.resource;

import com.evilbird.engine.action.events.Event;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;

public class ResourceTransferEvent implements Event
{
    private ResourceContainer recipient;
    private ResourceIdentifier resource;
    private float value;

    public ResourceTransferEvent(ResourceContainer recipient, ResourceIdentifier resource, float value) {
        this.recipient = recipient;
        this.resource = resource;
        this.value = value;
    }

    @Override
    public Item getSubject() {
        return (Item)recipient;
    }

    public ResourceContainer getContainer() {
        return recipient;
    }

    public ResourceIdentifier getResource() {
        return resource;
    }

    public float getValue() {
        return value;
    }
}
