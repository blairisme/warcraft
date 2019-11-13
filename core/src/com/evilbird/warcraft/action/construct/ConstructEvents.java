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
import com.evilbird.warcraft.object.unit.building.Building;

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

    public void notifyConstructStarted(Building building) {
        addEvent(building, ConstructStatus.Started);
    }

    public void notifyConstructUpgrade(Building building) {
        addEvent(building, ConstructStatus.Upgrading);
    }

    public void notifyConstructComplete(Building building) {
        addEvent(building, ConstructStatus.Complete);
    }

    public void notifyConstructCancelled(Building building) {
        addEvent(building, ConstructStatus.Cancelled);
    }

    private void addEvent(Building building, ConstructStatus status) {
        events.add(new ConstructEvent(building, status));
    }
}
