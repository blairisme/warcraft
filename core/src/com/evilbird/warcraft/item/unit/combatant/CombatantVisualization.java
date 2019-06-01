/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

/**
 * Provides methods that dictate how {@link Combatant} attributes are
 * displayed.
 *
 * @author Blair Butterworth
 */
public class CombatantVisualization
{
    public static final int ATTACK_FACTOR = 2;
    public static final int MOVEMENT_FACTOR = 8;

    private CombatantVisualization() {
    }

    public static String getDamageMinimum(Combatant combatant) {
        int value = combatant.getDamageMinimum() / ATTACK_FACTOR;
        return String.valueOf(value);
    }

    public static String getDamageMaximum(Combatant combatant) {
        int value = combatant.getDamageMaximum() / ATTACK_FACTOR;
        return String.valueOf(value);
    }

    public static String getDefence(Combatant combatant) {
        int value = combatant.getDefence() / ATTACK_FACTOR;
        return String.valueOf(value);
    }

    public static String getMovementSpeed(Combatant combatant) {
        int value = combatant.getMovementSpeed() / MOVEMENT_FACTOR;
        return String.valueOf(value);
    }

    public static String getRange(Combatant combatant) {
        int value = combatant.getRangeTiles();
        return String.valueOf(value);
    }

    public static String getSight(Combatant combatant) {
        int value = combatant.getSightTiles();
        return String.valueOf(value);
    }
}
