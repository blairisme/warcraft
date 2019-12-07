/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
