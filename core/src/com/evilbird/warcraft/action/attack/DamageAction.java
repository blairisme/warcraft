/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.math.RandomGenerator;
import com.evilbird.warcraft.item.common.capability.Destroyable;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

/**
 * Instances of this {@link Action} reduce the health of the given item.
 *
 * @author Blair Butterworth
 */
public class DamageAction extends BasicAction
{
    private Combatant attacker;
    private Destroyable target;
    private RandomGenerator random;

    public DamageAction(Combatant attacker, Destroyable target) {
        this.attacker = attacker;
        this.target = target;
        this.random = new RandomGenerator();
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
        int defence = target.getDefence();
        return time * defence;
    }

    private float getAttack(float time) {
        int attackMin = attacker.getDamageMinimum();
        int attackMax = attacker.getDamageMaximum();
        int attack = random.nextInt(attackMin, attackMax);
        return time * attack;
    }

    private float getDamage(float attack, float defence) {
       return Math.max(0, attack - defence);
    }

    private float setHealth(float damage) {
        float oldHealth = target.getHealth();
        float newHealth = Math.max(0, oldHealth - damage);
        target.setHealth(newHealth);
        return newHealth;
    }

    private boolean isDead(float health) {
        return health == 0f;
    }
}
