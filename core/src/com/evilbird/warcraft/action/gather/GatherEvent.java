/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.resource.ResourceQuantity;
import com.evilbird.warcraft.object.common.resource.ResourceType;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Event} are generated when a {@link Gatherer}
 * collects or deposits resources, or its operation is cancelled.
 *
 * @author Blair Butterworth
 */
public class GatherEvent implements Event
{
    private GameObject subject;
    private GameObject recipient;
    private GatherStatus status;
    private ResourceQuantity resource;

    public GatherEvent(GameObject subject, GatherStatus status) {
        this.status = status;
        this.subject = subject;
    }

    public GatherEvent(GameObject subject, GameObject recipient, ResourceQuantity resource, GatherStatus status) {
        this.subject = subject;
        this.status = status;
        this.recipient = recipient;
        this.resource = resource;
    }

    @Override
    public GameObject getSubject() {
        return subject;
    }

    public GatherStatus getStatus() {
        return status;
    }

    public GameObject getRecipient() {
        return recipient;
    }

    public ResourceType getType() {
        return resource.getType();
    }

    public float getValue() {
        return resource.getValue();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("subject", subject.getIdentifier())
            .append("recipient", recipient.getIdentifier())
            .append("status", status)
            .append("resource", resource)
            .toString();
    }
}
