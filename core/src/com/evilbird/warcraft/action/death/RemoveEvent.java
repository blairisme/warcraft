/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.death;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.object.GameObject;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

/**
 * Instances of this {@link Event} are produced when an {@link GameObject} is
 * removed.
 *
 * @author Blair Butterworth
 */
public class RemoveEvent implements Event
{
    private GameObject subject;

    /**
     * Constructs a new instance of this class given an {@link GameObject} that has
     * just been removed.
     *
     * @param newGameObject an {@code Item}. This parameter cannot be {@code null}.
     */
    public RemoveEvent(GameObject newGameObject) {
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
}
