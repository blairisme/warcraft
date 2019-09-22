/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.placeholder.PlaceholderStatus.Added;
import static com.evilbird.warcraft.action.placeholder.PlaceholderStatus.Removed;

/**
 * Helper class for generating placeholder events.
 *
 * @author Blair Butterworth
 */
public class PlaceholderEvents
{
    private Events events;

    @Inject
    public PlaceholderEvents(Events events) {
        this.events = events;
    }

    public void notifyPlaceholderAdded(Item builder, Item placeholder) {
        events.add(new PlaceholderEvent(builder, placeholder, Added));
    }

    public void notifyPlaceholderRemoved(Item builder, Item placeholder) {
        events.add(new PlaceholderEvent(builder, placeholder, Removed));
    }
}
