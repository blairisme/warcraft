/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.events.RecipientEvent;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.unit.building.Building;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

/**
 * Instances of this {@link Event} are generated when a {@link Building} is
 * constructed.
 *
 * @author Blair Butterworth
 */
public class ConstructEvent implements RecipientEvent
{
    private Building building;
    private GameObject builder;
    private ConstructStatus status;

    public ConstructEvent(Building building, GameObject builder, ConstructStatus status) {
        Objects.requireNonNull(building);
        Objects.requireNonNull(status);

        this.building = building;
        this.builder = builder;
        this.status = status;
    }

    public Building getBuilding() {
        return building;
    }

    public GameObject getBuilder() {
        return builder;
    }

    public ConstructStatus getStatus() {
        return status;
    }

    @Override
    public GameObject getSubject() {
        return building;
    }

    @Override
    public GameObject getRecipient() {
        return builder;
    }

    public boolean isConstructing() {
        return status == ConstructStatus.Started || status == ConstructStatus.Upgrading;
    }

    public boolean isComplete() {
        return status == ConstructStatus.Complete;
    }

    public boolean isCancelled() {
        return status == ConstructStatus.Cancelled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("building", building.getIdentifier())
            .append("builder", builder.getIdentifier())
            .append("status", status)
            .toString();
    }
}
