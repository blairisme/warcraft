/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.component;

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
public class AttackAction extends BasicAction
{
    private Combatant attacker;
    private Destroyable target;
    private RandomGenerator random;

    public AttackAction(Combatant attacker, Destroyable target) {
        this.attacker = attacker;
        this.target = target;
        this.random = new RandomGenerator();
    }

    @Override
    public boolean act(float time) {
        float damage = getDamage(time);
        float health = setHealth(damage);
        return isDead(health);
    }

    private float getDamage(float time) {
        int attack = getAttack();
        int defence = target.getDefence();
        float damage = Math.max(0, attack - defence);
        return time * damage;
    }

    private int getAttack() {
        int damageMin = attacker.getDamageMinimum();
        int damageMax = attacker.getDamageMaximum();
        return random.nextInt(damageMin, damageMax);
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
