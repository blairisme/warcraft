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
import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.warcraft.item.unit.MovableUnit;
import com.evilbird.warcraft.item.unit.Unit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;

/**
 * Instances of this class define a combatant: a {@link Unit} specialization
 * that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
@SerializedType("Combatant")
public class Combatant extends MovableUnit
{
    private int level;
    private float attackSpeed;
    private int damageMinimum;
    private int damageMaximum;
    private int range;

    public Combatant(Skin skin) {
        super(skin);
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public int getDamageMinimum() {
        return damageMinimum;
    }

    /**
     * The maximum amount of damage that the combatant deals with each attack.
     */
    public int getDamageMaximum() {
        return damageMaximum;
    }

    /**
     * How much damage the combatant always does with each attack, regardless
     * of the opponent’s armor.
     */
    public int getPiercingDamage() {
        return 0;
    }

    public int getLevel() {
        return level;
    }

    public int getRange() {
        return range;
    }

    public int getRangeTiles() {
        return range / TILE_WIDTH;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
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
            .append(attackSpeed, combatant.attackSpeed)
            .append(damageMinimum, combatant.damageMinimum)
            .append(damageMaximum, combatant.damageMaximum)
            .append(range, combatant.range)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(level)
            .append(attackSpeed)
            .append(damageMinimum)
            .append(damageMaximum)
            .append(range)
            .toHashCode();
    }
}

