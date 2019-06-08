/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.badlogic.gdx.math.GridPoint2;

/**
 * Defines options for specifying {@link Combatant} size. This is commonly used
 * to determine the appropriate size of assets used by the {@code Combatant}.
 *
 * @author Blair Butterworth
 */
public enum CombatantSize
{
    Regular;

    /**
     * Returns the {@link CombatantSize} in pixels.
     */
    public GridPoint2 getSize() {
        switch (this) {
            case Regular: return new GridPoint2(32, 32);
            default: throw new UnsupportedOperationException();
        }
    }
}
