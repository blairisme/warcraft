/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;

/**
 * Helper class for generating selection events.
 *
 * @author Blair Butterworth
 */
public class SelectEvents
{
    private SelectEvents() {
    }

    public static void notifySelected(Events events, Item item, boolean selected) {
        events.add(new SelectEvent(item, selected));
    }
}
