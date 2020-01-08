/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.common.create;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.object.GameObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

/**
 * Instances of this {@link Event} are produced when a new {@link GameObject} is
 * created.
 *
 * @author Blair Butterworth
 */
public class CreateEvent implements Event
{
    private GameObject subject;

    /**
     * constructs a new instance of this class given an {@link GameObject} that has
     * just been created.
     *
     * @param newGameObject an {@code Item}. This parameter cannot be {@code null}.
     */
    public CreateEvent(GameObject newGameObject) {
        Objects.requireNonNull(newGameObject);
        this.subject = newGameObject;
    }

    @Override
    public GameObject getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("subject", subject.getIdentifier())
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        CreateEvent that = (CreateEvent)obj;
        return new EqualsBuilder()
            .append(subject, that.subject)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(subject)
            .toHashCode();
    }
}
