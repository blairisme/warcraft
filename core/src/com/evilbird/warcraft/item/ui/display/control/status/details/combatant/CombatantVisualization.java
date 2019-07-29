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

    public static int getDamageMin(Combatant combatant) {
        return combatant.getPiercingDamage();
    }

    public static int getDamageMax(Combatant combatant) {
        int basicDamage = combatant.getBasicDamage();
        int pierceDamage = combatant.getPiercingDamage();
        return basicDamage + pierceDamage;
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

    public static int getArmour(Combatant combatant) {
        return combatant.getArmour();
    }

    public static int getSpeed(Combatant combatant) {
        return combatant.getMovementSpeed() / MOVEMENT_FACTOR;
    }

    public static int getRange(Combatant combatant) {
        return combatant.getAttackRange() / TILE_WIDTH;
    }

    public static int getSight(Combatant combatant) {
        return combatant.getSightTiles();
    }

    public static int getLevel(Combatant combatant) {
        return 1;
    }
}
