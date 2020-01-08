/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
