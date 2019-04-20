/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.resource;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Event} are produced when resources are transferred
 * to or from a {@link ResourceContainer}
 *
 * @author Blair Butterworth
 */
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("recipient", recipient.getIdentifier())
            .append("resource", resource)
            .append("value", value)
            .toString();
    }
}
