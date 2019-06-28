/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

import com.evilbird.engine.item.Item;

/**
 * Implementors of this interface represent an object that can be destroyed.
 *
 * @author Blair Butterworth
 */
public interface Destroyable extends Item
{
    /**
     * Returns how much damage the item’s armor absorbs with each attack.
     */
    int getDefence();

    float getHealth();

    void setHealth(float health);
}
