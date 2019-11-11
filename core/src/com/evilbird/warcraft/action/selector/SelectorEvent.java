/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selector;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.object.GameObject;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Event} are generated when a selector is
 * added or removed.
 *
 * @author Blair Butterworth
 */
public class SelectorEvent implements Event
{
    private GameObject builder;
    private GameObject owner;
    private SelectorStatus status;

    public SelectorEvent(GameObject builder, GameObject owner, SelectorStatus status) {
        this.builder = builder;
        this.owner = owner;
        this.status = status;
    }

    @Override
    public GameObject getSubject() {
        return owner;
    }

    public GameObject getOwner() {
        return builder;
    }

    public SelectorStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("builder", builder.getIdentifier())
            .append("owner", owner.getIdentifier())
            .append("status", status)
            .toString();
    }
}
