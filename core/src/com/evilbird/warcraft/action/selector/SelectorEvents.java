/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selector;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.selector.SelectorStatus.Added;
import static com.evilbird.warcraft.action.selector.SelectorStatus.Removed;

/**
 * Helper class for generating selector events.
 *
 * @author Blair Butterworth
 */
public class SelectorEvents
{
    private Events events;

    @Inject
    public SelectorEvents(Events events) {
        this.events = events;
    }

    public void notifyPlaceholderAdded(Item builder, Item selector) {
        events.add(new SelectorEvent(builder, selector, Added));
    }

    public void notifyPlaceholderRemoved(Item builder, Item selector) {
        events.add(new SelectorEvent(builder, selector, Removed));
    }
}
