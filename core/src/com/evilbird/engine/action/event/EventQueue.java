/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.event;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class EventQueue
{
    public List<Event> events;

    @Inject
    public EventQueue() {
        this.events = new ArrayList<>();
    }

    public List<Event> getAll() {
        return events;
    }

    public void add(Event event) {
        events.add(event);
    }

    public void clear() {
        events.clear();
    }
}
