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

import javax.inject.Inject;

public class CreateEvents
{
    private Events events;

    @Inject
    public CreateEvents(Events events) {
        this.events = events;
    }

    public void notifyCreate(Item item) {
        events.add(new CreateEvent(item));
    }
}
