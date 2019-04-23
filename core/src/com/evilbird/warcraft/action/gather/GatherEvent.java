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
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

/**
 * Instances of this {@link Event} are generated when a {@link Gatherer}
 * collects or deposits resources, or its operation is cancelled.
 *
 * @author Blair Butterworth
 */
public class GatherEvent implements Event
{
    private Gatherer subject;
    private GatherStatus status;
    private Item recipient;
    private ResourceType type;
    private float value;

    public GatherEvent(
        Gatherer subject,
        GatherStatus status)
    {
        this.status = status;
        this.subject = subject;
    }

    public GatherEvent(
        Gatherer subject,
        GatherStatus status,
        Item recipient,
        ResourceType type)
    {
        this.subject = subject;
        this.status = status;
        this.recipient = recipient;
        this.type = type;
    }

    public GatherEvent(
        Gatherer subject,
        GatherStatus status,
        Item recipient,
        ResourceType type,
        float value)
    {
        this.subject = subject;
        this.status = status;
        this.recipient = recipient;
        this.type = type;
        this.value = value;
    }

    @Override
    public Item getSubject() {
        return subject;
    }

    public GatherStatus getStatus() {
        return status;
    }

    public Item getRecipient() {
        return recipient;
    }

    public ResourceType getType() {
        return type;
    }

    public float getValue() {
        return value;
    }
}
