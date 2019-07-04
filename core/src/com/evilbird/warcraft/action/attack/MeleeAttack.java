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
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.attack.AttackDamage.getDamagedHealth;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.AdvancedSwordDamage;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.BasicSwordDamage;

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
    private transient float delay;

    @Inject
    public MeleeAttack() {
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
        int upgrade = getAttackUpgrade(combatant);
        float health = getDamagedHealth(combatant, target, upgrade);
        target.setHealth(health);
    }

    private int getAttackUpgrade(Combatant combatant) {
        Player player = getPlayer(combatant);
        return getAttackUpgrade(player);
    }

    private int getAttackUpgrade(Player player) {
        int upgrade = 0;
        upgrade += player.hasUpgrade(BasicSwordDamage) ? 2 : 0;
        upgrade += player.hasUpgrade(AdvancedSwordDamage) ? 2 : 0;
        return upgrade;
    }

    private boolean isTargetDead() {
        Destroyable target = (Destroyable)getTarget();
        return target.getHealth() == 0;
    }
}
