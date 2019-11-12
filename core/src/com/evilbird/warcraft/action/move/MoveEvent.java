/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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
            //.append("location", location.getSpatialReference())
            .append("status", status)
            .toString();
    }
}
