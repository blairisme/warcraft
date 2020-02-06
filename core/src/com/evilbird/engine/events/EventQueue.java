/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.events;

import com.evilbird.engine.common.collection.UnmodifiableIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

/**
 * Instances of this class represent a queue of events, note worthy phenomenon
 * that occurred within the last apply cycle.
 *
 * @author Blair Butterworth
 */
@Singleton
public class EventQueue implements Iterable<Event>, Events
{
    private static final Logger logger = LoggerFactory.getLogger(EventQueue.class);

    private Queue<Event> queue;
    private Queue<Event> delayed;
    private Map<Class<?>, Collection<Event>> types;

    @Inject
    public EventQueue() {
        queue = new ArrayDeque<>();
        delayed = new ArrayDeque<>();
        types = new HashMap<>();
    }

    @Override
    public void add(Event event) {
        logger.debug("Event added - {}", event);
        queue.add(event);
        addTyped(event);
    }

    public void addAll(Collection<Event> events) {
        for (Event event: events) {
            add(event);
        }
    }

    @Override
    public void addDelayed(Event event) {
        delayed.add(event);
    }

    private void addTyped(Event event) {
        Class<?> type = event.getClass();
        Collection<Event> typeQueue = types.containsKey(type) ? types.get(type) : new ArrayDeque<>();
        typeQueue.add(event);
        types.put(type, typeQueue);
    }

    public void update() {
        queue.clear();
        types.clear();

        addAll(delayed);
        delayed.clear();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Event> Collection<T> getEvents(Class<T> type) {
        if (types.containsKey(type)) {
            return (Collection<T>)types.get(type);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean hasEvents(Class<? extends Event> type) {
        return types.containsKey(type);
    }

    @Override
    public Iterator<Event> iterator() {
        return new UnmodifiableIterator<>(queue.iterator());
    }
}
