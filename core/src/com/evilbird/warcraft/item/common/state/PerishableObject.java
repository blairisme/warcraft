/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.state;

import com.evilbird.engine.item.Item;

/**
 * Implementors of this interface represent an object that can be destroyed.
 *
 * @author Blair Butterworth
 */
public interface PerishableObject extends Item
{
    /**
     * Returns how much damage the item’s armor absorbs with each attack.
     */
    int getArmour();

    /**
     * Returns the health of the item.
     */
    float getHealth();

    /**
     * Sets the health of the item.
     */
    void setHealth(float health);
}
