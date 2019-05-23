/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Movable;
import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.warcraft.item.unit.Unit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashSet;
import java.util.Set;

import static com.evilbird.warcraft.item.WarcraftItemConstants.MOVEMENT_FACTOR;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;

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
    private int range;
    private int movementSpeed;
    private Set<Identifier> movementCapability;

    public Combatant(Skin skin) {
        super(skin);
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

    public Set<Identifier> getMovementCapability() {
        return movementCapability;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public int getMovementSpeedTiles() {
        return movementSpeed / TILE_WIDTH;
    }

    public String getMovementSpeedText() {
        int value = movementSpeed / MOVEMENT_FACTOR;
        return String.valueOf(value);
    }

    public int getRange() {
        return range;
    }

    public int getRangeTiles() {
        return range / TILE_WIDTH;
    }

    public String getRangeText() {
        return String.valueOf(getRangeTiles());
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

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setMovementCapability(Identifier capability) {
        this.movementCapability.clear();
        this.movementCapability.add(capability);
    }

    public void setRange(int range) {
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
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

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

