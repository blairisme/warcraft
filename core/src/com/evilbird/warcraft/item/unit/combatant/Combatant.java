/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Movable;
import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.warcraft.item.unit.Unit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Instances of this class define a combatant: a {@link Unit} specialization
 * that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
@SerializedType("Combatant")
public class Combatant extends Unit implements Movable
{
    private int level;
    private int damageMinimum;
    private int damageMaximum;
    private float range;
    private float movementSpeed;
    private Set<Identifier> movementCapability;

    public Combatant() {
        super();
        movementCapability = new HashSet<>();
    }

    public int getDamageMinimum() {
        return damageMinimum;
    }

    public int getDamageMaximum() {
        return damageMaximum;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public float getMovementSpeed() {
        return movementSpeed;
    }

    public Set<Identifier> getMovementCapability() {
        return movementCapability;
    }

    public float getRange() {
        return range;
    }

    public void setDamageMinimum(int damageMinimum) {
        this.damageMinimum = damageMinimum;
    }

    public void setDamageMaximum(int damageMaximum) {
        this.damageMaximum = damageMaximum;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setMovementCapability(Identifier capability) {
        this.movementCapability.clear();
        this.movementCapability.add(capability);
    }

    public void setMovementCapability(Collection<Identifier> capability) {
        this.movementCapability.clear();
        this.movementCapability.addAll(capability);
    }

    public void setRange(float range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("unit")
            .append("level", level)
            .append("damageMinimum", damageMinimum)
            .append("damageMaximum", damageMaximum)
            .append("range", range)
            .append("movementSpeed", movementSpeed)
            .append("movementCapability", movementCapability)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        Combatant combatant = (Combatant)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(level, combatant.level)
            .append(damageMinimum, combatant.damageMinimum)
            .append(damageMaximum, combatant.damageMaximum)
            .append(range, combatant.range)
            .append(movementSpeed, combatant.movementSpeed)
            .append(movementCapability, combatant.movementCapability)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(level)
            .append(damageMinimum)
            .append(damageMaximum)
            .append(range)
            .append(movementSpeed)
            .append(movementCapability)
            .toHashCode();
    }
}

