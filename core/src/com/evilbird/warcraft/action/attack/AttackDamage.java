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
import com.evilbird.engine.common.math.RandomGenerator;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

/**
 * Calculates the attack damage to be applied to a given target.
 *
 * @author Blair Butterworth
 */
public class AttackDamage
{
    private static RandomGenerator random = new RandomGenerator();

    private AttackDamage() {
    }

    public static float getDamagedHealth(Combatant combatant, Destroyable target, int upgrade) {
        int damage = getDamage(combatant, target, upgrade);
        float health = target.getHealth();
        return Math.max(0, health - damage);
    }

    public static int getDamage(Combatant combatant, Destroyable target, int upgrade) {
        int attack = getAttack(combatant, upgrade);
        int defence = getDefense(combatant, target);
        return Math.max(0, attack - defence);
    }

    private static int getAttack(Combatant combatant, int upgrade) {
        int attackMin = getAttackMinimum(combatant);
        int attackMax = getAttackMaximum(combatant, upgrade);
        return random.nextInt(attackMin, attackMax);
    }

    private static int getAttackMinimum(Combatant combatant) {
        int damage = combatant.getDamageMinimum();
        float speed = combatant.getAttackSpeed();
        return Math.round(damage * speed);
    }

    private static int getAttackMaximum(Combatant combatant, int upgrade) {
        int damage = combatant.getDamageMaximum();
        float speed = combatant.getAttackSpeed();
        return Math.round((damage + upgrade) * speed);
    }

    private static int getDefense(Combatant combatant, Destroyable target) {
        int defense = target.getDefence();
        float speed = combatant.getAttackSpeed();
        return Math.round(defense * speed);
    }
}
