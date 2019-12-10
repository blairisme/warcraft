/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;

import javax.inject.Inject;
import javax.inject.Provider;
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
    private static transient Random random = new Random();
    private transient Provider<DeathAction> actions;

    /**
     * Constructs a new instance of this class given a factory that can produce
     * {@link DeathAction DeathActions}.
     */
    @Inject
    public AttackDamage(Provider<DeathAction> actions) {
        this.actions = actions;
    }

    /**
     * Reduces the health of the given target according to the damage dealt by
     * the given attacker. If the targets health is reduced to zero then a
     * {@link DeathAction} is assigned to it.
     *
     * @param attacker  the attacking unit.
     * @param target    the target of the attacker.
     */
    public void apply(OffensiveObject attacker, PerishableObject target) {
        if (target.isAlive()) {
            target.setHealth(getDamagedHealth(attacker, target));

            if (target.isDead()) {
                assignDeath(target);
            }
        }
    }

    /**
     * Reduces the health of the given {@link PerishableObject} to zero and
     * assigns a {@link DeathAction} to it.
     *
     * @param object the {@code PerishableObject} to damage.
     */
    public void applyFull(PerishableObject object) {
        if (object.isAlive()) {
            object.setHealth(0);
            assignDeath(object);
        }
    }

    private float getDamagedHealth(OffensiveObject attacker, PerishableObject target) {
        int damage = vulnerable(target) ? getDamage(attacker, target) : 0;
        float health = target.getHealth();
        return Math.max(0, health - damage);
    }

    private boolean vulnerable(PerishableObject target) {
        return target.getArmour() != Integer.MAX_VALUE;
    }

    private int getDamage(OffensiveObject combatant, PerishableObject target) {
        int armour = target.getArmour();
        int basic = combatant.getBasicDamage();
        int piercing = combatant.getPiercingDamage();
        return getDamage(armour, basic, piercing);
    }

    private static int getDamage(int armour, int basic, int piercing) {
        int damage = Math.max(basic - armour, 0) + piercing;
        return random.nextBoolean() ? damage : (int)Math.ceil(damage / 2.0);
    }

    private void assignDeath(PerishableObject target) {
        Action death = actions.get();
        death.setSubject(target);
        target.removeActions();
        target.addAction(death);
    }
}
