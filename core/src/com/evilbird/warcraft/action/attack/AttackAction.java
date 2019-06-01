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
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.common.math.RandomGenerator;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} reduce the health of the Actions target.
 * The amount of damage inflicted will be chosen at random from between the
 * Action subjects damage {@link Combatant#getDamageMinimum() minimum} and
 * {@link Combatant#getDamageMaximum() maximum}. The Action will end when the
 * targets health reaches zero.
 *
 * @author Blair Butterworth
 */
public class AttackAction extends BasicAction
{
    private transient RandomGenerator random;

    @Inject
    public AttackAction() {
        random = new RandomGenerator();
    }

    public static AttackAction attack() {
        return new AttackAction();
    }

    @Override
    public boolean act(float time) {
        float defence = getDefence(time);
        float attack = getAttack(time);
        float damage = getDamage(attack, defence);
        float health = setHealth(damage);
        return isDead(health);
    }

    private float getDefence(float time) {
        Destroyable target = (Destroyable)getTarget();
        int defence = target.getDefence();
        return time * defence;
    }

    private float getAttack(float time) {
        Combatant attacker = (Combatant)getItem();
        int attackMin = attacker.getDamageMinimum();
        int attackMax = attacker.getDamageMaximum();
        int attack = random.nextInt(attackMin, attackMax);
        return time * attack;
    }

    private float getDamage(float attack, float defence) {
       return Math.max(0, attack - defence);
    }

    private float setHealth(float damage) {
        Destroyable target = (Destroyable)getTarget();
        float oldHealth = target.getHealth();
        float newHealth = Math.max(0, oldHealth - damage);
        target.setHealth(newHealth);
        return newHealth;
    }

    private boolean isDead(float health) {
        return health == 0f;
    }
}
