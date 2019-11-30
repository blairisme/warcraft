/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.common.capability;

import com.evilbird.engine.object.GameObject;

/**
 * Implementors of this interface represent an object that can be destroyed.
 *
 * @author Blair Butterworth
 */
public interface PerishableObject extends GameObject
{
    /**
     * Returns how much damage the {@code PerishableObject PerishableObjects}
     * armour absorbs with each attack.
     */
    int getArmour();

    /**
     * Returns the health of the {@code PerishableObject}.
     */
    float getHealth();

    /**
     * Returns the team number of the {@code PerishableObject PerishableObjects}
     * owner.
     */
    int getTeam();

    /**
     * Returns the type of terrain the {@code PerishableObject} resides in.
     */
    TerrainType getTerrainType();

    /**
     * Returns whether the {@code PerishableObject} is visible to potential
     * attackers.
     */
    boolean isAttackable();

    /**
     * Sets the health of the {@code PerishableObject}.
     */
    void setHealth(float health);

    /**
     * Determines if the given {@code PerishableObject} is "alive" of not.
     * Specifically if the {@code PerishableObject#getHealth() health} of the
     * {@code PerishableObject} is greater than zero.
     */
    default boolean isAlive() {
        return getHealth() > 0;
    }

    /**
     * Returns whether the given {@code PerishableObject} is an enemy.
     */
    default boolean isEnemy(PerishableObject other) {
        int thisTeam = this.getTeam();
        int otherTeam = other.getTeam();
        return thisTeam != otherTeam;
    }
}
