/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.warcraft.item.common.capability.OffensiveObject;
import com.evilbird.warcraft.item.common.capability.PerishableObject;

import java.util.Random;

/**
 * Calculates the attack damage to be applied to a given target. Damage is
 * determined by subtracting the target’s Armor from the attacking unit’s
 * Basic Damage, and then adding in the attacking unit’s Piercing Damage. The
 * attacking unit has a chance of doing either full damage or half damage with
 * each attack.
 *
 * @author Blair Butterworth
 */
public class AttackDamage
{
    private static Random random = new Random();

    /**
     * Disable construction of static helper class.
     */
    private AttackDamage() {
    }

    /**
     * Determines the health of the given target after a the given attacker has
     * attacked it.
     *
     * @param attacker  the attacking unit.
     * @param target    the target of the attacker.
     * @return          the targets health minus the attack damage inflicted by
     *                  the attacker.
     */
    public static float getDamagedHealth(OffensiveObject attacker, PerishableObject target) {
        int damage = getDamage(attacker, target);
        float health = target.getHealth();
        return Math.max(0, health - damage);
    }

    private static int getDamage(OffensiveObject combatant, PerishableObject target) {
        int armour = target.getArmour();
        int basic = combatant.getBasicDamage();
        int piercing = combatant.getPiercingDamage();
        return getDamage(armour, basic, piercing);
    }

    private static int getDamage(int armour, int basic, int piercing) {
        int damage = Math.max(basic - armour, 0) + piercing;
        return random.nextBoolean() ? damage : (int)Math.ceil(damage / 2.0);
    }
}
