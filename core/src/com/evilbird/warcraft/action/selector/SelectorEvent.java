/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
