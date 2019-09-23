/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

/**
 * Helper class for generating construction events.
 *
 * @author Blair Butterworth
 */
public class ConstructEvents
{
    private Events events;

    @Inject
    public ConstructEvents(Events events) {
        this.events = events;
    }

    public void notifyConstructStarted(Item builder, Building building) {
        addEvent(builder, building, ConstructStatus.Started);
    }

    public void notifyConstructComplete(Item builder, Building building) {
        addEvent(builder, building, ConstructStatus.Complete);
    }

    public void notifyConstructCancelled(Item builder, Building building) {
        addEvent(builder, building, ConstructStatus.Cancelled);
    }

    private void addEvent(Item builder, Building building, ConstructStatus status) {
        events.add(new ConstructEvent(builder, building, status));
    }
}
