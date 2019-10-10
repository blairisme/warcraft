/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.critter;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.item.common.state.MovableObject;
import com.evilbird.warcraft.item.common.state.MovementCapability;
import com.evilbird.warcraft.item.unit.Unit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.evilbird.warcraft.item.common.state.MovementCapability.None;

/**
 * Represents a critter, an animal that inhabits the game world. Critters can
 * move and be attacked, but cannot attack themselves.
 *
 * @author Blair Butterworth
 */
public class Critter extends Unit implements MovableObject
{
    private int movementSpeed;
    private MovementCapability movementCapability;

    public Critter(Skin skin) {
        super(skin);
        movementCapability = None;
    }

    public MovementCapability getMovementCapability() {
        return movementCapability;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementCapability(MovementCapability capability) {
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
