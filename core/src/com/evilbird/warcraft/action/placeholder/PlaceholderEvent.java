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
import com.evilbird.warcraft.item.placeholder.Placeholder;

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
}
