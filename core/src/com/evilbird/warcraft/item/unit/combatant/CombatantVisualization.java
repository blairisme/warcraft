/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerUpgrade;
import com.evilbird.warcraft.item.projectile.ProjectileType;

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

    public static String getDamageMinimum(Combatant combatant) {
        return String.valueOf(combatant.getDamageMinimum());
    }

    public static String getDamageMaximum(Combatant combatant) {
        String damage = String.valueOf(combatant.getDamageMaximum());
        String upgrade = getDamageUpgrade(combatant);
        return damage + upgrade;
    }

    private static String getDamageUpgrade(Combatant combatant) {
        Player player = UnitOperations.getPlayer(combatant);
        PlayerUpgrade upgrade = getDamageUpgradeType(combatant);
        int damageUpgrade = 0;//player.getUpgrade(upgrade);
        return damageUpgrade > 0 ? " + " + damageUpgrade : "";
    }

    private static PlayerUpgrade getDamageUpgradeType(Combatant combatant) {
        if (combatant instanceof RangedCombatant) {
            RangedCombatant rangedCombatant = (RangedCombatant)combatant;
            return getDamageUpgradeType(rangedCombatant.getProjectileType());
        }
        return PlayerUpgrade.SwordDamage;
    }

    private static PlayerUpgrade getDamageUpgradeType(ProjectileType projectileType) {
        switch (projectileType) {
            case Arrow: return PlayerUpgrade.ArrowDamage;
            case Axe: return PlayerUpgrade.AxeDamage;
            case Cannon: return PlayerUpgrade.CannonDamage;
            default: throw new UnsupportedOperationException("Unknown projectile type");
        }
    }

    public static String getDefence(Combatant combatant) {
        return String.valueOf(combatant.getDefence());
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
