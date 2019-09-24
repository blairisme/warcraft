/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.status.details.combatant;

import com.evilbird.warcraft.item.common.upgrade.UpgradeRank;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;

/**
 * Provides methods that dictate how {@link Combatant} attributes are
 * displayed.
 *
 * @author Blair Butterworth
 */
public class CombatantVisualization
{
    public static final int MOVEMENT_FACTOR = 8;

    private CombatantVisualization() {
    }

    public static int getDamageMin(Combatant combatant) {
        return combatant.getPiercingDamage();
    }

    public static int getDamageMax(Combatant combatant) {
        int basicDamage = combatant.getBasicDamage();
        int pierceDamage = combatant.getPiercingDamage();
        return basicDamage + pierceDamage;
    }

    public static int getDamageUpgrade(Combatant combatant) {
        int basic = combatant.getBasicDamage(UpgradeRank.None);
        int upgraded = combatant.getBasicDamage();
        return upgraded - basic;
    }

    public static int getArmour(Combatant combatant) {
        return combatant.getArmour();
    }

    public static int getArmourUpgrade(Combatant combatant) {
        int basic = combatant.getArmour(UpgradeRank.None);
        int upgraded = combatant.getArmour();
        return upgraded - basic;
    }

    public static int getSpeed(Combatant combatant) {
        return combatant.getMovementSpeed() / MOVEMENT_FACTOR;
    }

    public static int getRange(Combatant combatant) {
        return combatant.getAttackRange() / TILE_WIDTH;
    }

    public static int getSight(Combatant combatant) {
        return combatant.getSight() / TILE_WIDTH;
    }

    public static int getLevel(Combatant combatant) {
        return 1;
    }
}
