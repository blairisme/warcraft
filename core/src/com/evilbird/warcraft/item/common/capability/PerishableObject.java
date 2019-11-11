/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.capability;

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
     * Returns whether the {@code PerishableObject} is visible to potential
     * attackers.
     */
    boolean isAttackable();

    /**
     * Sets the health of the {@code PerishableObject}.
     */
    void setHealth(float health);
}
