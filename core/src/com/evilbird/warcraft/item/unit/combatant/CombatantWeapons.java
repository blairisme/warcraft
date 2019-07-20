/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.evilbird.warcraft.item.unit.UnitType;

/**
 * Defines weapons used by {@link Combatant}s. Useful for assigning the
 * visual and audio assets that match the combatants attack type.
 *
 * @author Blair Butterworth
 */
public class CombatantWeapons
{
    private CombatantWeapons() {
    }

    public static String getWeaponName(UnitType type) {
        if (type.isRanged()) {
            return type.isOrc() ? "axe" : "bow";
        }
        if (type.isSiege()) {
            return "siege";
        }
        return "sword";
    }
}
