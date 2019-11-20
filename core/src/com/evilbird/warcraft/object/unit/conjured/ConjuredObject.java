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
        basicDamage = 0;
        piercingDamage = 0;
    }

    /**
     * Returns the attack capability of the {@code Tower}.
     */
    @Override
    public OffensiveCapability getAttackCapability() {
        return Air;
    }

    /**
     * Returns whether the number of targets the {@code OffensiveObject} can
     * attack in a given instant.
     */
    @Override
    public OffensivePlurality getAttackPlurality() {
        return Multiple;
    }

    /**
     * Returns the distance that the {@code Tower} can reach with its
     * attacks.
     */
    @Override
    public int getAttackRange() {
        return attackRange;
    }

    /**
     * Returns the rate at which the {@code Tower} attacks.
     */
    @Override
    public float getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Returns whether the {@code OffensiveObject} is visible to potential
     * attackers.
     */
    @Override
    public boolean isAttackable() {
        return true;
    }

    /**
     * Returns the amount of damage that the {@code Tower} deals
     * with each attack. If the {@code Tower} belongs to a {@code Player},
     * then the upgrades applied to player will use to determine the resulting
     * value.
     */
    @Override
    public int getBasicDamage() {
        return basicDamage;
    }

    /**
     * Returns the damage the {@code Tower} always does with each attack,
     * regardless of the opponent’s armor.
     */
    @Override
    public int getPiercingDamage() {
        return piercingDamage;
    }

    /**
     * Sets the rate at which the {@code Tower} attacks.
     */
    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * Sets the distance that the {@code Tower} can reach with its
     * attacks.
     */
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }
    
    /**
     * Sets the maximum amount of damage that the {@code Tower} deals
     * with each attack.
     */
    public void setBasicDamage(int basicDamage) {
        this.basicDamage = basicDamage;
    }

    /**
     * Sets the damage the {@code Tower} always does with each attack,
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
