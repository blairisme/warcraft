/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.spatial.SpatialObject;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.capability.OffensiveCapability;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.OffensivePlurality;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.common.value.FixedValue;
import com.evilbird.warcraft.object.common.value.Value;
import com.evilbird.warcraft.object.common.value.ValueProperty;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.evilbird.warcraft.object.common.capability.OffensiveCapability.Proximity;
import static com.evilbird.warcraft.object.common.capability.OffensivePlurality.Individual;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.common.value.FixedValue.Zero;

/**
 * Instances of this class define a combatant: a {@link Unit} specialization
 * that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class Combatant extends Unit implements MovableObject, OffensiveObject, SpatialObject
{
    private boolean attackable;
    private float attackTime;
    private Value attackSpeed;
    private Value basicDamage;
    private Value piercingDamage;
    private Value movementSpeed;
    private TerrainType movementCapability;

    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin  a {@link Skin} instance containing, amongst others, a
     *              {@link UnitStyle}.
     */
    public Combatant(Skin skin) {
        super(skin);
        this.attackTime = 0;
        this.attackSpeed = Zero;
        this.piercingDamage = Zero;
        this.basicDamage = Zero;
        this.movementSpeed = Zero;
        this.attackable = true;
        this.movementCapability = TerrainType.None;
    }

    /**
     * Returns the attack capability of the {@code Combatant}.
     */
    @Override
    public OffensiveCapability getAttackCapability() {
        return Proximity;
    }

    /**
     * Returns whether the number of targets the {@code Combatant} can
     * attack in a given instant.
     */
    @Override
    public OffensivePlurality getAttackPlurality() {
        return Individual;
    }

    /**
     * Returns the rate at which the {@code Combatant} attacks.
     */
    @Override
    public float getAttackSpeed() {
        return attackSpeed.getValue(this);
    }

    /**
     * Returns the rate at which the {@code Combatant} attacks.
     */
    public Value getAttackSpeedValue() {
        return attackSpeed;
    }

    /**
     * Returns the amount of time remaining until the attacker can attack again.
     */
    public float getAttackTime() {
        return attackTime;
    }

    /**
     * Returns whether the {@code OffensiveObject} is visible to potential
     * attackers.
     */
    @Override
    public boolean isAttackable() {
        return attackable;
    }

    /**
     * Returns the rate at which the {@code Combatant} attacks.
     */
    public ValueProperty getAttackSpeedProperty() {
        return new ValueProperty(this::getAttackSpeedValue, this::setAttackSpeed);
    }

    /**
     * Returns the distance that the {@code Combatant} can reach with its
     * attacks, specified in world pixels.
     */
    @Override
    public int getAttackRange() {
        return tiles(1);
    }

    /**
     * Returns the amount of damage that the {@code Combatant} deals
     * with each attack. If the {@code Combatant} belongs to a {@code Player},
     * then the upgrades applied to player will use to determine the resulting
     * value.
     */
    @Override
    public int getBasicDamage() {
        return (int)basicDamage.getValue(this);
    }

    /**
     * Returns the amount of damage that the {@code Combatant} deals
     * with each attack, without having been upgraded.
     */
    public Value getBasicDamageValue() {
        return basicDamage;
    }

    /**
     * Returns the amount of damage that the {@code Combatant} deals
     * with each attack, without having been upgraded.
     */
    public ValueProperty getBasicDamageProperty() {
        return new ValueProperty(this::getBasicDamageValue, this::setBasicDamage);
    }

    /**
     * Returns the damage the {@code Combatant} always does with each attack,
     * regardless of the opponent’s armor.
     */
    @Override
    public int getPiercingDamage() {
        return (int)piercingDamage.getValue(this);
    }

    /**
     * Returns the damage the {@code Combatant} always does with each attack,
     * regardless of the opponent’s armor.
     */
    public Value getPiercingDamageValue() {
        return piercingDamage;
    }

    /**
     * Returns the damage the {@code Combatant} always does with each attack,
     * regardless of the opponent’s armor.
     */
    public ValueProperty getPiercingDamageProperty() {
        return new ValueProperty(this::getPiercingDamageValue, this::setPiercingDamage);
    }

    /**
     * Returns the {@link TerrainType movement capability} of the
     * {@code Combatant}, those types of terrain the {@code Combatant} can
     * traverse across.
     */
    @Override
    public TerrainType getMovementCapability() {
        return movementCapability;
    }

    /**
     * Returns the movement speed of the {@code Combatant}, specified in pixels
     * per second.
     */
    @Override
    public int getMovementSpeed() {
        return (int)movementSpeed.getValue(this);
    }

    /**
     * Returns the movement speed of the {@code Combatant}, specified in pixels
     * per second.
     */
    public Value getMovementSpeedValue() {
        return movementSpeed;
    }

    /**
     * Returns the movement speed of the {@code Combatant}, specified in pixels
     * per second.
     */
    public ValueProperty getMovementSpeedProperty() {
        return new ValueProperty(this::getMovementSpeedValue, this::setMovementSpeed);
    }

    /**
     * Sets the rate at which the {@code Combatant} attacks.
     */
    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = new FixedValue(attackSpeed);
    }

    /**
     * Sets the rate at which the {@code Combatant} attacks.
     */
    public void setAttackSpeed(Value attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * Sets the amount of time remaining until the attacker can attack again.
     */
    public void setAttackTime(float attackTime) {
        this.attackTime = attackTime;
    }

    /**
     * Sets whether the {@code OffensiveObject} is visible to potential
     * attackers.
     */
    public void setAttackable(boolean attackVisibility) {
        this.attackable = attackVisibility;
    }

    /**
     * Sets the maximum amount of damage that the {@code Combatant} deals
     * with each attack.
     */
    public void setBasicDamage(int basicDamage) {
        this.basicDamage = new FixedValue(basicDamage);
    }

    /**
     * Sets the maximum amount of damage that the {@code Combatant} deals
     * with each attack.
     */
    public void setBasicDamage(Value basicDamage) {
        this.basicDamage = basicDamage;
    }

    /**
     * Sets the damage the {@code Combatant} always does with each attack,
     * regardless of the opponent’s armor.
     */
    public void setPiercingDamage(int piercingDamage) {
        this.piercingDamage = new FixedValue(piercingDamage);
    }

    /**
     * Sets the damage the {@code Combatant} always does with each attack,
     * regardless of the opponent’s armor.
     */
    public void setPiercingDamage(Value piercingDamage) {
        this.piercingDamage = piercingDamage;
    }

    /**
     * Sets the {@link TerrainType movement capability} of the
     * {@code Combatant}, those types of terrain the {@code Combatant} can
     * traverse across.
     */
    public void setMovementCapability(TerrainType capability) {
        this.movementCapability = capability;
    }

    /**
     * Sets the movement speed of the {@code Combatant}, specified in pixels
     * per second.
     */
    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = new FixedValue(movementSpeed);
    }

    /**
     * Sets the movement speed of the {@code Combatant}, specified in pixels
     * per second.
     */
    public void setMovementSpeed(Value movementSpeed) {
        this.movementSpeed = movementSpeed;
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

        Combatant combatant = (Combatant)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(attackable, combatant.attackable)
            .append(attackSpeed, combatant.attackSpeed)
            .append(attackTime, combatant.attackTime)
            .append(basicDamage, combatant.basicDamage)
            .append(piercingDamage, combatant.piercingDamage)
            .append(movementSpeed, combatant.movementSpeed)
            .append(movementCapability, combatant.movementCapability)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(attackable)
            .append(attackSpeed)
            .append(attackTime)
            .append(basicDamage)
            .append(piercingDamage)
            .append(movementSpeed)
            .append(movementCapability)
            .toHashCode();
    }
}

