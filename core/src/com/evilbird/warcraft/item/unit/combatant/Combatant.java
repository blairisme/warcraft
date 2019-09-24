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
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.unit.MovableUnit;
import com.evilbird.warcraft.item.unit.Unit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this class define a combatant: a {@link Unit} specialization
 * that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class Combatant extends MovableUnit
{
    private float attackSpeed;
    private int attackRange;
    private int basicDamage;
    private int piercingDamage;

    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * it visual and auditory presentation.
     *
     * @param skin  a {@link Skin} instance containing, amongst others, a
     *              {@link ViewableStyle}.
     */
    public Combatant(Skin skin) {
        super(skin);
    }

    /**
     * Returns the rate at which the {@code Combatant} attacks.
     */
    public float getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Returns the distance that the {@code Combatant} can reach with its
     * attacks.
     */
    public int getAttackRange() {
        return attackRange;
    }

    /**
     * Returns the maximum amount of damage that the {@code Combatant} deals
     * with each attack.
     */
    public int getBasicDamage() {
        return basicDamage;
    }

    /**
     * Returns the damage the {@code Combatant} always does with each attack,
     * regardless of the opponent’s armor.
     */
    public int getPiercingDamage() {
        return piercingDamage;
    }

    /**
     * Sets the rate at which the {@code Combatant} attacks.
     */
    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * Sets the distance that the {@code Combatant} can reach with its
     * attacks.
     */
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    /**
     * Sets the maximum amount of damage that the {@code Combatant} deals
     * with each attack.
     */
    public void setBasicDamage(int basicDamage) {
        this.basicDamage = basicDamage;
    }

    /**
     * Sets the damage the {@code Combatant} always does with each attack,
     * regardless of the opponent’s armor.
     */
    public void setPiercingDamage(int piercingDamage) {
        this.piercingDamage = piercingDamage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("unit")
            .append("attackSpeed", attackSpeed)
            .append("attackRange", attackRange)
            .append("basicDamage", basicDamage)
            .append("piercingDamage", piercingDamage)
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
            .append(attackSpeed, combatant.attackSpeed)
            .append(attackRange, combatant.attackRange)
            .append(basicDamage, combatant.basicDamage)
            .append(piercingDamage, combatant.piercingDamage)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(attackSpeed)
            .append(attackRange)
            .append(basicDamage)
            .append(piercingDamage)
            .toHashCode();
    }
}

