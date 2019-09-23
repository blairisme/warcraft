/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

/**
 * Helper class for generating production events.
 *
 * @author Blair Butterworth
 */
public class ProduceEvents
{
    private Events events;

    @Inject
    public ProduceEvents(Events events) {
        this.events = events;
    }

    public void notifyProductionStarted(Building building) {
        events.add(new ProduceEvent(building, ProduceStatus.Started));
    }

    public void notifyProductionCompleted(Building building) {
        events.add(new ProduceEvent(building, ProduceStatus.Complete));
    }

    public void notifyProductionCancelled(Building building) {
        events.add(new ProduceEvent(building, ProduceStatus.Cancelled));
    }
}
