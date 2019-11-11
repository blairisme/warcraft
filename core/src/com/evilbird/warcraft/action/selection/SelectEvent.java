/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selection;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.object.GameObject;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Event} are produced when an {@link GameObject} is
 * selected or unselected.
 *
 * @author Blair Butterworth
 */
public class SelectEvent implements Event
{
    private GameObject subject;
    private boolean selected;

    public SelectEvent(GameObject subject, boolean selected) {
        this.subject = subject;
        this.selected = selected;
    }

    @Override
    public GameObject getSubject() {
        return subject;
    }

    public boolean getSelected() {
        return selected;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("subject", subject.getIdentifier())
            .append("selected", selected)
            .toString();
    }
}
