/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

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

    private AttackDamage() {
    }

    public static float getDamagedHealth(Combatant combatant, Destroyable target, int upgrade) {
        int damage = getDamage(combatant, target, upgrade);
        float health = target.getHealth();
        return Math.max(0, health - damage);
    }

    public static int getDamage(Combatant combatant, Destroyable target, int upgrade) {
        int armour = target.getArmour();
        int basic = combatant.getBasicDamage() + upgrade;
        int piercing = combatant.getPiercingDamage();
        return getDamage(armour, basic, piercing);
    }

    private static int getDamage(int armour, int basic, int piercing) {
        int damage = Math.max(basic - armour, 0) + piercing;
        return random.nextBoolean() ? damage : (int)Math.ceil(damage / 2.0);
    }
}
