/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.state;

import com.evilbird.engine.common.lang.Animated;
import com.evilbird.engine.common.lang.Audible;
import com.evilbird.engine.item.Item;

/**
 * Implementors of this interface represent a game object that can attack a
 * perishable game object
 *
 * @author Blair Butterworth
 */
public interface OffensiveObject extends Item, Animated, Audible
{
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
}
