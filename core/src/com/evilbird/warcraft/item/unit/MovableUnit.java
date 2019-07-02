/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.item.common.movement.Movable;
import com.evilbird.warcraft.item.common.movement.MovementCapability;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.evilbird.warcraft.item.common.movement.MovementCapability.None;

/**
 * A {@link Unit} specialization that can move throughout the game world.
 *
 * @author Blair Butterworth
 */
public class MovableUnit extends Unit implements Movable
{
    private int movementSpeed;
    private MovementCapability movementCapability;

    public MovableUnit(Skin skin) {
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

        MovableUnit movableUnit = (MovableUnit)obj;
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
