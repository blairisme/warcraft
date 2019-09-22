/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.remove;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;

/**
 * Helper class for generating removal events.
 *
 * @author Blair Butterworth
 */
public class RemoveEvents
{
    /**
     * Disable construction of static helper class.
     */
    private RemoveEvents() {
    }

    public static void notifyRemove(Events events, Item item) {
        events.add(new RemoveEvent(item));
    }
}
