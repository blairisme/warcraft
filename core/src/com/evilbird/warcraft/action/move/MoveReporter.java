/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.event.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemNode;

import javax.inject.Inject;

public class MoveReporter implements MoveObserver
{
    private Events events;

    @Inject
    public MoveReporter(Events events) {
        this.events = events;
    }

    @Override
    public void onMove(Item subject, ItemNode location) {
        events.add(new MoveEvent(subject, location));
    }
}
