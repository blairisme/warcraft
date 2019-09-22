/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.create;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;

/**
 * @author Blair Butterworth
 */
public class CreateEvents
{
    private CreateEvents() {
    }

    public static void notifyItemCreated(Events events, Item item) {
        events.add(new CreateEvent(item));
    }
}
