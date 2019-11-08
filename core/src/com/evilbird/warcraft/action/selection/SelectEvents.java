/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selection;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

/**
 * Helper class for generating selection events.
 *
 * @author Blair Butterworth
 */
public class SelectEvents
{
    private Events events;

    @Inject
    public SelectEvents(Events events) {
        this.events = events;
    }

    public void notifySelected(Item item, boolean selected) {
        events.add(new SelectEvent(item, selected));
    }
}
