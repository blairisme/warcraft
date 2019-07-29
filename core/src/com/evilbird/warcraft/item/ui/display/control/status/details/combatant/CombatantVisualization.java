/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.status.details.combatant;

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

    public static String getDamage(Combatant combatant) {
        int basicDamage = combatant.getBasicDamage();
        int pierceDamage = combatant.getPiercingDamage();
        int totalDamage = basicDamage + pierceDamage;
        return pierceDamage + "-" + totalDamage;
    }

//    private static String getDamageUpgrade(Combatant combatant) {
//        Player player = UnitOperations.getPlayer(combatant);
//        PlayerUpgrade upgrade = getDamageUpgradeType(combatant);
//        int damageUpgrade = 0;//player.getUpgrade(upgrade);
//        return damageUpgrade > 0 ? " + " + damageUpgrade : "";
//    }
//
//    private static PlayerUpgrade getDamageUpgradeType(Combatant combatant) {
//        if (combatant instanceof RangedCombatant) {
//            RangedCombatant rangedCombatant = (RangedCombatant)combatant;
//            return getDamageUpgradeType(rangedCombatant.getProjectileType());
//        }
//        return PlayerUpgrade.BasicSwordDamage;
//    }
//
//    private static PlayerUpgrade getDamageUpgradeType(ProjectileType projectileType) {
//        switch (projectileType) {
//            case Arrow: return PlayerUpgrade.BasicArrowDamage;
//            case Axe: return PlayerUpgrade.BasicAxeDamage;
//            case Cannon: return PlayerUpgrade.BasicCannonDamage;
//            default: throw new UnsupportedOperationException("Unknown projectile type");
//        }
//    }

    public static String getArmour(Combatant combatant) {
        return String.valueOf(combatant.getArmour());
    }

    public static String getSpeed(Combatant combatant) {
        int value = combatant.getMovementSpeed() / MOVEMENT_FACTOR;
        return String.valueOf(value);
    }

    public static String getRange(Combatant combatant) {
        int value = combatant.getAttackRange() / TILE_WIDTH;
        return String.valueOf(value);
    }

    public static String getSight(Combatant combatant) {
        int value = combatant.getSightTiles();
        return String.valueOf(value);
    }

    public static String getLevel(Combatant combatant) {
        return "1";
    }
}
