/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.events;

import com.evilbird.engine.object.GameObject;

/**
 * Implementors of this interface represent an instance of a note worthy
 * phenomenon: an event.
 *
 * @author Blair Butterworth
 */
public interface Event
{
    /**
     * The {@link GameObject} involved in the event.
     *
     * @return an {@code Item}.
     */
    GameObject getSubject();
}
