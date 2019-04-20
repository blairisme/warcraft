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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger logger = LoggerFactory.getLogger(EventQueue.class);

    private Queue<Event> queue;
    private Map<Class<?>, Collection<Event>> types;

    @Inject
    public EventQueue() {
        queue = new ArrayDeque<>();
        types = new HashMap<>();
    }

    public void add(Event event) {
        logger.debug("Event added - {}", event);
        queue.add(event);
        addTyped(event);
    }

    private void addTyped(Event event) {
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
    public Iterator<Event> iterator() {
        return new UnmodifiableIterator<>(queue.iterator());
    }

    @SuppressWarnings("unchecked")
    public <T extends Event> Collection<T> getEvents(Class<T> type) {
        if (types.containsKey(type)) {
            return (Collection<T>)types.get(type);
        }
        return Collections.emptyList();
    }

    public boolean hasEvents(Class<? extends Event> type) {
        return types.containsKey(type);
    }
}
