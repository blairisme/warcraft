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
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.data.resource.ResourceContainer;
import com.evilbird.warcraft.data.resource.ResourceType;
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
    private float value;

    public TransferEvent(
        ResourceContainer recipient,
        ResourceType resource,
        float value)
    {
        this.recipient = recipient;
        this.resource = resource;
        this.value = value;
    }

    @Override
    public GameObject getSubject() {
        return recipient;
    }

    public ResourceContainer getContainer() {
        return recipient;
    }

    public ResourceType getResource() {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        TransferEvent that = (TransferEvent)obj;
        return new EqualsBuilder()
            .append(value, that.value)
            .append(recipient, that.recipient)
            .append(resource, that.resource)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(recipient)
            .append(resource)
            .append(value)
            .toHashCode();
    }
}
