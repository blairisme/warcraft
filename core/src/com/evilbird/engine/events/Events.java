/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.events;

import java.util.Collection;

/**
 * Instances of this interface represent a repository of {@link Event Events},
 * note worthy phenomenon generated by game operations that occur within the
 * last apply cycle.
 *
 * @author Blair Butterworth
 */
public interface Events
{
    /**
     * Stores a new {@link Event} the in the event repository.
     */
    void add(Event event);

    /**
     * Returns all {@link Event Events} stored in the repository that have the
     * given type.
     */
    <T extends Event> Collection<T> getEvents(Class<T> type);
}
