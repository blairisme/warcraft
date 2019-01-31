/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.event;

import com.evilbird.engine.common.collection.UnmodifiableIterator;

import javax.inject.Inject;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * @author Blair Butterworth
 */
public class EventStream implements Iterable<Event>
{
    private Queue<Event> queue;

    @Inject
    public EventStream() {
        queue = new ArrayDeque<>();
    }

    public void add(Event event) {
        queue.add(event);
    }

    public void clear() {
        queue.clear();
    }

    @Override
    public Iterator<Event> iterator() {
        return new UnmodifiableIterator<>(queue.iterator());
    }
}
