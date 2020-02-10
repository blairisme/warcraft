/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.data.product.Product;
import com.evilbird.warcraft.object.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.produce.ProduceStatus.Cancelled;
import static com.evilbird.warcraft.action.produce.ProduceStatus.Complete;
import static com.evilbird.warcraft.action.produce.ProduceStatus.Started;

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

    public void notifyProductionStarted(Building building, Product product) {
        events.add(new ProduceEvent(building, product, Started));
    }

    public void notifyProductionCompleted(Building building, Product product) {
        events.add(new ProduceEvent(building, product, Complete));
    }

    public void notifyProductionCancelled(Building building, Product product) {
        events.add(new ProduceEvent(building, product, Cancelled));
    }
}
