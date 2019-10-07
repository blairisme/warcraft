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
 * Defines {@link Combatant} varieties, primarily differentiated by attack and
 * mobility differences.
 *
 * @author Blair Butterworth
 */
public enum CombatantVariety
{
    ConjuredCombatant,
    ConjuredFlyingCombatant,
    FlyingCombatant,
    MeleeCombatant,
    RangedCombatant,
    NavalCombatant,
    ScoutCombatant,
    SiegeCombatant,
    SubmarineCombatant;

    public static CombatantVariety forType(UnitType type) {
        if (type.isNeutral()) {
            return type.isFlying() ? ConjuredFlyingCombatant : ConjuredCombatant;
        }
        if (type.isFlyingAssault()) {
            return FlyingCombatant;
        }
        if (type.isFlyingScout()) {
            return ScoutCombatant;
        }
        if (type.isRanged()) {
            return RangedCombatant;
        }
        if (type.isShip()) {
            return NavalCombatant;
        }
        if (type.isSiege()) {
            return SiegeCombatant;
        }
        if (type.isSubmarine()) {
            return SubmarineCombatant;
        }
        return MeleeCombatant;
    }
}
