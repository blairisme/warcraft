/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

/**
 * Instances of this {@link Event} are generated when a {@link Building} is
 * constructed.
 *
 * @author Blair Butterworth
 */
public class ConstructEvent implements Event
{
    private Item builder;
    private Building building;
    private ConstructStatus status;

    public ConstructEvent(Item builder, Building building, ConstructStatus status) {
        Objects.requireNonNull(builder);
        Objects.requireNonNull(building);
        Objects.requireNonNull(status);

        this.builder = builder;
        this.building = building;
        this.status = status;
    }

    public Item getBuilder() {
        return builder;
    }

    public Building getBuilding() {
        return building;
    }

    public ConstructStatus getStatus() {
        return status;
    }

    @Override
    public Item getSubject() {
        return building;
    }

    public boolean isConstructing() {
        return status == ConstructStatus.Started;
    }

    public boolean isComplete() {
        return status == ConstructStatus.Complete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("builder", builder.getIdentifier())
            .append("building", building.getIdentifier())
            .append("status", status)
            .toString();
    }
}
