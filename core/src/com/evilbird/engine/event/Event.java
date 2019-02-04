/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.event;

import com.evilbird.engine.item.Item;

/**
 * Implementors of this interface represent an instance of a note worthy
 * phenomenon: an event.
 *
 * @author Blair Butterworth
 */
public interface Event
{
    /**
     * The {@link Item} involved in the event.
     *
     * @return an <code>Item</code>.
     */
    Item getSubject();
}
