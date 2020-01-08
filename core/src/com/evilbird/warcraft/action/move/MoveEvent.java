/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.spatial.GameObjectNode;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Event} are produced when an {@link GameObject} moves from
 * one {@link GameObjectNode} to another.
 *
 * @author Blair Butterworth
 */
public class MoveEvent implements Event
{
    private GameObject subject;
    private GameObjectNode location;
    private MoveStatus status;

    public MoveEvent(GameObject subject, MoveStatus status) {
        this(subject, null, status);
    }

    public MoveEvent(GameObject subject, GameObjectNode location, MoveStatus status) {
        this.subject = subject;
        this.location = location;
        this.status = status;
    }

    @Override
    public GameObject getSubject() {
        return subject;
    }

    public GameObjectNode getLocation() {
        return location;
    }

    public MoveStatus getStatus() {
        return status;
    }

    public boolean isFinished() {
        return status == MoveStatus.Complete || status == MoveStatus.Failed;
    }

    public boolean isUpdate() {
        return status == MoveStatus.Updated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("subject", subject.getIdentifier())
            .append("status", status)
            .toString();
    }
}
