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
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.spatial.ItemNode;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Event} are produced when an {@link Item} moves from
 * one {@link ItemNode} to another.
 *
 * @author Blair Butterworth
 */
public class MoveEvent implements Event
{
    private Item subject;
    private ItemNode location;
    private MoveStatus status;

    public MoveEvent(Item subject, MoveStatus status) {
        this(subject, null, status);
    }

    public MoveEvent(Item subject, ItemNode location, MoveStatus status) {
        this.subject = subject;
        this.location = location;
        this.status = status;
    }

    @Override
    public Item getSubject() {
        return subject;
    }

    public ItemNode getLocation() {
        return location;
    }

    public MoveStatus getStatus() {
        return status;
    }

    public boolean isComplete() {
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
