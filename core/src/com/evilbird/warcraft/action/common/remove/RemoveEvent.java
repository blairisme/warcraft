/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.remove;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.item.Item;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

/**
 * Instances of this {@link Event} are produced when an {@link Item} is
 * removed.
 *
 * @author Blair Butterworth
 */
public class RemoveEvent implements Event
{
    private Item subject;

    /**
     * Constructs a new instance of this class given an {@link Item} that has
     * just been removed.
     *
     * @param newItem an {@code Item}. This parameter cannot be {@code null}.
     */
    public RemoveEvent(Item newItem) {
        Objects.requireNonNull(newItem);
        this.subject = newItem;
    }

    @Override
    public Item getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("subject", subject.getIdentifier())
            .toString();
    }
}
