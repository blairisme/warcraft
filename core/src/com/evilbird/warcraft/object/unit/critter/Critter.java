/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.critter;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.spatial.SpatialObject;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.unit.Unit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.evilbird.warcraft.object.common.capability.TerrainType.None;

/**
 * Represents a critter, an animal that inhabits the game world. Critters can
 * move and be attacked, but cannot attack themselves.
 *
 * @author Blair Butterworth
 */
public class Critter extends Unit implements MovableObject, SpatialObject
{
    private int movementSpeed;
    private TerrainType movementCapability;

    public Critter(Skin skin) {
        super(skin);
        movementCapability = None;
    }

    @Override
    public TerrainType getMovementCapability() {
        return movementCapability;
    }

    @Override
    public int getMovementSpeed() {
        return movementSpeed;
    }

    @Override
    public boolean isEnemy(PerishableObject other) {
        return false;
    }

    public void setMovementCapability(TerrainType capability) {
        this.movementCapability = capability;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("unit")
            .append("movementSpeed", movementSpeed)
            .append("movementCapability", movementCapability)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        Critter movableUnit = (Critter)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(movementSpeed, movableUnit.movementSpeed)
            .append(movementCapability, movableUnit.movementCapability)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(movementSpeed)
            .append(movementCapability)
            .toHashCode();
    }
}
