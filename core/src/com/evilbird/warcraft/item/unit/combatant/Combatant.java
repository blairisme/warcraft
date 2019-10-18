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
import com.evilbird.warcraft.item.common.state.MovableObject;
import com.evilbird.warcraft.item.common.state.MovementCapability;
import com.evilbird.warcraft.item.common.state.OffensiveCapability;
import com.evilbird.warcraft.item.common.state.OffensiveObject;
import com.evilbird.warcraft.item.common.upgrade.UpgradeSequence;
import com.evilbird.warcraft.item.common.upgrade.UpgradeValue;
import com.evilbird.warcraft.item.unit.Unit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.state.OffensiveCapability.Proximity;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSequence.ZeroInt;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.None;

/**
 * Instances of this class define a combatant: a {@link Unit} specialization
 * that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class Combatant extends Unit implements MovableObject, OffensiveObject
{
    private float attackSpeed;
    private int piercingDamage;
    private UpgradeValue<Integer> basicDamage;
    private int movementSpeed;
    private MovementCapability movementCapability;

    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin  a {@link Skin} instance containing, amongst others, a
     *              {@link ViewableStyle}.
     */
    public Combatant(Skin skin) {
        super(skin);
        this.attackSpeed = 0;
        this.piercingDamage = 0;
        this.basicDamage = ZeroInt;
        this.movementSpeed = 0;
        this.movementCapability = MovementCapability.None;
    }

    /**
     * Returns the attack capability of the {@code Combatant}.
     */
    @Override
    public OffensiveCapability getAttackCapability() {
        return Proximity;
    }

    /**
     * Returns the rate at which the {@code Combatant} attacks.
     */
    @Override
    public float getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Returns the distance that the {@code Combatant} can reach with its
     * attacks.
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
        return getUpgradeValue(basicDamage);
    }

    /**
     * Returns the amount of damage that the {@code Combatant} deals
     * with each attack, without having been upgraded.
     */
    public int getBasicDamageBaseValue() {
        return basicDamage.getBaseValue();
    }

    /**
     * Returns the damage the {@code Combatant} always does with each attack,
     * regardless of the opponent’s armor.
     */
    @Override
    public int getPiercingDamage() {
        return piercingDamage;
    }

    /**
     * Returns the {@link MovementCapability movement capability} of the
     * {@code Combatant}, those types of terrain the {@code Combatant} can
     * traverse across.
     */
    @Override
    public MovementCapability getMovementCapability() {
        return movementCapability;
    }

    /**
     * Returns the movement speed of the {@code Combatant}, specified in pixels
     * per second.
     */
    @Override
    public int getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Sets the rate at which the {@code Combatant} attacks.
     */
    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * Sets the maximum amount of damage that the {@code Combatant} deals
     * with each attack.
     */
    public void setBasicDamage(int basicDamage) {
        this.basicDamage = new UpgradeSequence<>(None, basicDamage);
    }

    /**
     * Sets the maximum amount of damage that the {@code Combatant} deals
     * with each attack.
     */
    public void setBasicDamage(UpgradeValue<Integer> basicDamage) {
        this.basicDamage = basicDamage;
    }

    /**
     * Sets the damage the {@code Combatant} always does with each attack,
     * regardless of the opponent’s armor.
     */
    public void setPiercingDamage(int piercingDamage) {
        this.piercingDamage = piercingDamage;
    }

    /**
     * Sets the {@link MovementCapability movement capability} of the
     * {@code Combatant}, those types of terrain the {@code Combatant} can
     * traverse across.
     */
    public void setMovementCapability(MovementCapability capability) {
        this.movementCapability = capability;
    }

    /**
     * Sets the movement speed of the {@code Combatant}, specified in pixels
     * per second.
     */
    public void setMovementSpeed(int movementSpeed) {
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
            .append(attackSpeed, combatant.attackSpeed)
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
            .append(attackSpeed)
            .append(basicDamage)
            .append(piercingDamage)
            .append(movementSpeed)
            .append(movementCapability)
            .toHashCode();
    }
}

