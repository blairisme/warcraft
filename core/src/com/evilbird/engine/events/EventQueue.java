/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.events;

import com.evilbird.engine.common.collection.UnmodifiableIterator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

/**
 * Instances of this class represent a queue of events, note worthy phenomenon
 * that occurred within the last update cycle.
 *
 * @author Blair Butterworth
 */
@Singleton
public class EventQueue implements Iterable<Event>
{
    private Queue<com.evilbird.engine.events.Event> queue;
    private Map<Class<?>, Collection<Event>> types;

    @Inject
    public EventQueue() {
        queue = new ArrayDeque<>();
        types = new HashMap<>();
    }

    public void add(com.evilbird.engine.events.Event event) {
        queue.add(event);
        addTyped(event);
    }

    private void addTyped(com.evilbird.engine.events.Event event) {
        Class<?> type = event.getClass();
        Collection<Event> typeQueue = types.containsKey(type) ? types.get(type) : new ArrayDeque<>();
        typeQueue.add(event);
        types.put(type, typeQueue);
    }

    public void clear() {
        queue.clear();
        types.clear();
    }

    @Override
    public Iterator<com.evilbird.engine.events.Event> iterator() {
        return new UnmodifiableIterator<>(queue.iterator());
    }

    @SuppressWarnings("unchecked")
    public <T extends Event> Collection<T> getEvents(Class<T> type) {
        if (types.containsKey(type)) {
            return (Collection<T>)types.get(type);
        }
        return Collections.emptyList();
    }
}
