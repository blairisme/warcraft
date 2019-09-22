/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;

public class MoveEvents
{
    private MoveEvents() {
    }

    public static void notifyMoveCancelled(Events events, Item subject) {
        events.add(new MoveEvent(subject, MoveStatus.Cancelled));
    }
}
