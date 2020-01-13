/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
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

    public void notifyConstructStarted(Building building, GameObject builder) {
        addEvent(building, builder, ConstructStatus.Started);
    }

    public void notifyConstructComplete(Building building, GameObject builder) {
        addEvent(building, builder, ConstructStatus.Complete);
    }

    public void notifyConstructCancelled(Building building, GameObject builder) {
        addEvent(building, builder, ConstructStatus.Cancelled);
    }

    public void notifyUpgradeStarted(Building building) {
        addEvent(building, building, ConstructStatus.Upgrading);
    }

    public void notifyUpgradeComplete(Building building, Building upgrade) {
        addEvent(upgrade, building, ConstructStatus.Upgrading);
    }

    public void notifyUpgradeCancelled(Building building) {
        addEvent(building, building, ConstructStatus.Upgrading);
    }

    private void addEvent(Building building, GameObject builder, ConstructStatus status) {
        events.add(new ConstructEvent(building, builder, status));
    }
}
