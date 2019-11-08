/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selector;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.item.Item;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Event} are generated when a selector is
 * added or removed.
 *
 * @author Blair Butterworth
 */
public class SelectorEvent implements Event
{
    private Item builder;
    private Item placeholder;
    private SelectorStatus status;

    public SelectorEvent(Item builder, Item placeholder, SelectorStatus status) {
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

    public SelectorStatus getStatus() {
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
