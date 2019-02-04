/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.event.Events;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

public class SelectReporter implements SelectObserver
{
    private Events events;

    @Inject
    public SelectReporter(Events events) {
        this.events = events;
    }

    @Override
    public void onSelect(Item item, boolean selected) {
        events.add(new SelectEvent(item, selected));
    }
}
