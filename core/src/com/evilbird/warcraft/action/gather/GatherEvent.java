/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.data.resource.ResourceQuantity;
import com.evilbird.warcraft.data.resource.ResourceType;
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
