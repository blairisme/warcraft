/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.common.capability;

import com.evilbird.engine.common.lang.Animated;
import com.evilbird.engine.common.lang.Audible;

/**
 * Implementors of this interface represent a game object that can attack a
 * perishable game object.
 *
 * @author Blair Butterworth
 */
public interface OffensiveObject extends PerishableObject, Animated, Audible
{
    /**
     * Returns the attack capability of the {@code OffensiveObject}.
     */
    OffensiveCapability getAttackCapability();

    /**
     * Returns whether the number of targets the {@code OffensiveObject} can
     * attack in a given instant.
     */
    OffensivePlurality getAttackPlurality();

    /**
     * Returns the distance that the {@code OffensiveObject} can reach with its
     * attacks, specified in world units.
     */
    int getAttackRange();

    /**
     * Returns the rate at which the {@code OffensiveObject} attacks, specified
     * in seconds.
     */
    float getAttackSpeed();

    /**
     * Returns the amount of time remaining until the attacker can attack again.
     */
    float getAttackTime();

    /**
     * Returns the amount of damage that the {@code OffensiveObject} deals
     * with each attack, specified in health units per attack. If the
     * {@code OffensiveObject} belongs to a {@code Player}, then the upgrades
     * applied to player will use to determine the resulting value.
     */
    int getBasicDamage();

    /**
     * Returns the damage the {@code OffensiveObject} always does with each
     * attack, regardless of the opponent’s armor. Piercing damage is specified
     * in health points per attack.
     */
    int getPiercingDamage();

    /**
     * Returns the distance that the {@code OffensiveObject} can detect
     * targets, specified in world units.
     */
    int getSight();

    /**
     * Returns whether the {@link OffensiveObject} can attack the given
     * {@link PerishableObject}.
     */
    default boolean isAttackPossible(PerishableObject perishable) {
        OffensiveCapability capability = getAttackCapability();
        return capability.supports(perishable.getTerrainType());
    }

    /**
     * Sets the amount of time remaining until the attacker can attack again.
     */
    void setAttackTime(float attackTime);
}
