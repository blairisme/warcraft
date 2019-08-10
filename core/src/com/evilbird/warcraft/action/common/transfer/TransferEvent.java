/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.transfer;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Event} are produced when resources are transferred
 * to or from a {@link ResourceContainer}.
 *
 * @author Blair Butterworth
 */
public class TransferEvent implements Event
{
    private ResourceContainer recipient;
    private ResourceType resource;
    private float oldValue;
    private float newValue;

    public TransferEvent(
        ResourceContainer recipient,
        ResourceType resource,
        float oldValue,
        float newValue)
    {
        this.recipient = recipient;
        this.resource = resource;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public Item getSubject() {
        return (Item)recipient;
    }

    public ResourceContainer getContainer() {
        return recipient;
    }

    public ResourceType getResource() {
        return resource;
    }

    public float getValue() {
        return newValue;
    }

    public float getOldValue() {
        return oldValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("recipient", recipient.getIdentifier())
            .append("resource", resource)
            .append("oldValue", oldValue)
            .append("newValue", newValue)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        TransferEvent that = (TransferEvent)obj;
        return new EqualsBuilder()
            .append(oldValue, that.oldValue)
            .append(newValue, that.newValue)
            .append(recipient, that.recipient)
            .append(resource, that.resource)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(recipient)
            .append(resource)
            .append(oldValue)
            .append(newValue)
            .toHashCode();
    }
}
