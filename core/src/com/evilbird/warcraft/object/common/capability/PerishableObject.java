/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.common.capability;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.data.player.Player;

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
     * Returns the team the {@code PerishableObject} belongs to.
     */
    Player getTeam();

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
     * Determines if the given {@code PerishableObject} is "alive" or not.
     * Specifically if the {@code PerishableObject#getHealth() health} of the
     * {@code PerishableObject} is greater than zero.
     */
    default boolean isAlive() {
        return getHealth() > 0;
    }

    /**
     * Determines if the given {@code PerishableObject} is "dead" or not.
     * Specifically if the {@code PerishableObject#getHealth() health} of the
     * {@code PerishableObject} is zero.
     */
    default boolean isDead() {
        return getHealth() == 0;
    }

    /**
     * Returns whether the given {@code PerishableObject} is an enemy.
     */
    default boolean isEnemy(PerishableObject other) {
        Player playerA = this.getTeam();
        Player playerB = other.getTeam();
        int teamA = playerA != null ? playerA.getTeam() : -1;
        int teamB = playerB != null ? playerB.getTeam() : -1;
        return teamA != teamB;
    }
}
