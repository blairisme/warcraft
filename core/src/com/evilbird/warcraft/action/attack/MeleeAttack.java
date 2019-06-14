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
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
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
public class MeleeAttack extends BasicAction
{
    private transient RandomGenerator random;
    private transient float delay;

    @Inject
    public MeleeAttack() {
        random = new RandomGenerator();
    }

    public static MeleeAttack meleeAttack() {
        return new MeleeAttack();
    }

    @Override
    public boolean act(float time) {
        if (! readyToAttack()) {
            reduceAttackDelay(time);
        } else {
            attackTarget();
        }
        return isTargetDead();
    }

    private boolean readyToAttack() {
        return delay == 0;
    }

    private void reduceAttackDelay(float time) {
        delay = Math.max(delay - time, 0);
    }

    private void attackTarget() {
        Combatant combatant = (Combatant)getItem();
        Destroyable target = (Destroyable)getTarget();
        setTargetHealth(combatant, target);
        setCombatantAnimation(combatant);
        delay = combatant.getAttackSpeed();
    }

    private void setCombatantAnimation(Combatant combatant) {
        combatant.resetAnimation();
        combatant.setAnimation(UnitAnimation.Attack);
        combatant.setSound(UnitSound.Attack);
    }

    private void setTargetHealth(Combatant combatant, Destroyable target) {
        float health = target.getHealth();
        float damage = getAttackDamage(combatant, target);
        float newHealth = Math.max(0, health - damage);
        target.setHealth(newHealth);
    }

    private float getAttackDamage(Combatant combatant, Destroyable target) {
        int attackMin = Math.round(combatant.getDamageMinimum() * combatant.getAttackSpeed());
        int attackMax = Math.round(combatant.getDamageMaximum() * combatant.getAttackSpeed());
        int attack = random.nextInt(attackMin, attackMax);
        int defence = Math.round(target.getDefence() * combatant.getAttackSpeed());
        return Math.max(0, attack - defence);
    }

    private boolean isTargetDead() {
        Destroyable target = (Destroyable)getTarget();
        return target.getHealth() == 0;
    }
}
