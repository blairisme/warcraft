/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.ui.placement.Placeholder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Event} are generated when a {@link Placeholder} is
 * added or removed.
 *
 * @author Blair Butterworth
 */
public class PlaceholderEvent implements Event
{
    private Item builder;
    private Item placeholder;
    private PlaceholderStatus status;

    public PlaceholderEvent(Item builder, Item placeholder, PlaceholderStatus status) {
        this.builder = builder;
        this.placeholder = placeholder;
        this.status = status;
    }

    @Override
    public Item getSubject() {
        return placeholder;
    }

    public Item getBuilder() {
        return builder;
    }

    public PlaceholderStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("builder", builder.getIdentifier())
            .append("placeholder", placeholder.getIdentifier())
            .append("status", status)
            .toString();
    }
}
