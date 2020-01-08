/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.object.common.capability.OffensiveCapability;
import com.evilbird.warcraft.object.common.capability.OffensivePlurality;
import com.evilbird.warcraft.object.common.capability.RangedOffensiveObject;
import com.evilbird.warcraft.object.projectile.Projectile;
import com.evilbird.warcraft.object.projectile.ProjectileType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.evilbird.warcraft.object.common.capability.OffensiveCapability.Air;
import static com.evilbird.warcraft.object.common.capability.OffensivePlurality.Individual;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Arrow;

/**
 * Represents a {@link Building} that has offensive capabilities: it can attack
 * other game objects. For example, a cannon tower.
 *
 * @author Blair Butterworth
 */
public class Tower extends Building implements RangedOffensiveObject
{
    private float attackSpeed;
    private float attackTime;
    private int attackRange;
    private int basicDamage;
    private int piercingDamage;
    private ProjectileType projectileType;

    public Tower(Skin skin) {
        super(skin);
        attackRange = 0;
        attackSpeed = 0;
        attackTime = 0;
        basicDamage = 0;
        piercingDamage = 0;
        projectileType = Arrow;
    }

    /**
     * Returns the attack capability of the {@code Tower}.
     */
    @Override
    public OffensiveCapability getAttackCapability() {
        return Air;
    }

    /**
     * Returns whether the number of targets the {@code Tower} can
     * attack in a given instant.
     */
    @Override
    public OffensivePlurality getAttackPlurality() {
        return Individual;
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
     * Returns the objects current projectile, if any.
     */
    @Override
    public Projectile getProjectile() {
        return (Projectile)getAssociatedItem();
    }

    /**
     * Returns the type of projectile used when the ranged offensive unit
     * attacks.
     */
    @Override
    public ProjectileType getProjectileType() {
        return projectileType;
    }

    /**
     * Sets the distance that the {@code Tower} can reach with its
     * attacks.
     */
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    /**
     * Sets the rate at which the {@code Tower} attacks.
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

    /**
     * Sets the type of projectile used when the ranged offensive unit
     * attacks.
     */
    public void setProjectileType(ProjectileType projectileType) {
        this.projectileType = projectileType;
    }

    /**
     * Sets the projectile to be used by the ranged object.
     */
    @Override
    public void setProjectile(Projectile projectile) {
        setAssociatedItem(projectile);
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

        Tower combatant = (Tower)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(attackRange, combatant.attackRange)
            .append(attackSpeed, combatant.attackSpeed)
            .append(attackTime, combatant.attackTime)
            .append(basicDamage, combatant.basicDamage)
            .append(piercingDamage, combatant.piercingDamage)
            .append(projectileType, combatant.projectileType)
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
            .append(projectileType)
            .toHashCode();
    }
}
