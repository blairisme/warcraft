/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.conjured;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.object.common.capability.OffensiveCapability;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.OffensivePlurality;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.evilbird.warcraft.object.common.capability.OffensiveCapability.Air;
import static com.evilbird.warcraft.object.common.capability.OffensivePlurality.Multiple;

/**
 * Represents a magically conjured game object that has offensive capabilities:
 * it can attack other game objects.
 *
 * @author Blair Butterworth
 */
public class ConjuredObject extends Unit implements OffensiveObject
{
    private float attackSpeed;
    private float attackTime;
    private int attackRange;
    private int basicDamage;
    private int piercingDamage;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * an {@link UnitStyle}, specifying the visual and auditory
     * presentation of the new ConjuredObject.
     *
     * @param skin a {@code Skin} instance containing a {@code UnitStyle}.
     *
     * @throws NullPointerException if the given skin is {@code null} or
     *                              doesn't contain a {@code UnitStyle}.
     */
    public ConjuredObject(Skin skin) {
        super(skin);
        attackRange = 0;
        attackSpeed = 0;
        attackTime = 0;
        basicDamage = 0;
        piercingDamage = 0;
    }

    /**
     * Returns the attack capability of the {@code ConjuredObject}.
     */
    @Override
    public OffensiveCapability getAttackCapability() {
        return Air;
    }

    /**
     * Returns whether the number of targets the {@code ConjuredObject} can
     * attack in a given instant.
     */
    @Override
    public OffensivePlurality getAttackPlurality() {
        return Multiple;
    }

    /**
     * Returns the distance that the {@code ConjuredObject} can reach with its
     * attacks.
     */
    @Override
    public int getAttackRange() {
        return attackRange;
    }

    /**
     * Returns the rate at which the {@code ConjuredObject} attacks.
     */
    @Override
    public float getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Returns the amount of time remaining until the attacker can attack again.
     */
    public float getAttackTime() {
        return attackTime;
    }

    /**
     * Returns whether the {@code ConjuredObject} is visible to potential
     * attackers.
     */
    @Override
    public boolean isAttackable() {
        return true;
    }

    /**
     * Returns whether the given {@code ConjuredObject} is an enemy.
     */
    @Override
    public boolean isEnemy(PerishableObject other) {
        return false;
    }

    /**
     * Returns the amount of damage that the {@code ConjuredObject} deals
     * with each attack. If the {@code ConjuredObject} belongs to a
     * {@code Player}, then the upgrades applied to player will use to
     * determine the resulting value.
     */
    @Override
    public int getBasicDamage() {
        return basicDamage;
    }

    /**
     * Returns the damage the {@code ConjuredObject} always does with each
     * attack, regardless of the opponent’s armor.
     */
    @Override
    public int getPiercingDamage() {
        return piercingDamage;
    }

    /**
     * Sets the distance that the {@code ConjuredObject} can reach with its
     * attacks.
     */
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    /**
     * Sets the rate at which the {@code ConjuredObject} attacks.
     */
    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * Sets the amount of time remaining until the attacker can attack again.
     */
    public void setAttackTime(float attackTime) {
        this.attackTime = attackTime;
    }

    /**
     * Sets the maximum amount of damage that the {@code ConjuredObject} deals
     * with each attack.
     */
    public void setBasicDamage(int basicDamage) {
        this.basicDamage = basicDamage;
    }

    /**
     * Sets the damage the {@code ConjuredObject} always does with each attack,
     * regardless of the opponent’s armor.
     */
    public void setPiercingDamage(int piercingDamage) {
        this.piercingDamage = piercingDamage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("identifier", getIdentifier())
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        ConjuredObject combatant = (ConjuredObject)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(attackRange, combatant.attackRange)
            .append(attackSpeed, combatant.attackSpeed)
            .append(attackTime, combatant.attackTime)
            .append(basicDamage, combatant.basicDamage)
            .append(piercingDamage, combatant.piercingDamage)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(attackRange)
            .append(attackSpeed)
            .append(attackTime)
            .append(basicDamage)
            .append(piercingDamage)
            .toHashCode();
    }
}
