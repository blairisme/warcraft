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

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.action.attack.AttackDamage.getDamagedHealth;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.item.common.query.UnitOperations.reorient;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.MeleeDamage1;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.MeleeDamage2;

/**
 * Instances of this {@link Action} reduce the health of the Actions target.
 * The Action will end when the targets health reaches zero.
 *
 * @author Blair Butterworth
 */
public class MeleeAttack extends BasicAction
{
    private transient float delay;
    private transient boolean initialized;

    @Inject
    public MeleeAttack() {
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            initialize();
        }
        if (! readyToAttack()) {
            return delayAttack(time);
        }
        if (attackTarget()) {
            return attackComplete();
        }
        return ActionIncomplete;
    }

    @Override
    public void reset() {
        super.reset();
        delay = -1;
        initialized = false;
    }

    @Override
    public void restart() {
        super.restart();
        delay = -1;
        initialized = false;
    }

    private boolean initialized() {
        return initialized;
    }

    private void initialize() {
        Combatant combatant = (Combatant)getItem();
        combatant.setAnimation(UnitAnimation.Attack);
        combatant.setSound(UnitSound.Attack);

        Destroyable target = (Destroyable)getTarget();
        reorient(combatant, target, false);

        delay = combatant.getAttackSpeed();
        initialized = true;
    }

    private boolean readyToAttack() {
        return delay == 0;
    }

    private boolean delayAttack(float time) {
        delay = Math.max(delay - time, 0);
        return ActionIncomplete;
    }

    private boolean attackTarget() {
        Combatant combatant = (Combatant)getItem();
        combatant.setSound(UnitSound.Attack);

        Destroyable target = (Destroyable)getTarget();
        setTargetHealth(combatant, target);

        reorient(combatant, target, false);
        delay = combatant.getAttackSpeed();
        return target.getHealth() == 0;
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
        upgrade += player.hasUpgrade(MeleeDamage1) ? 2 : 0;
        upgrade += player.hasUpgrade(MeleeDamage2) ? 2 : 0;
        return upgrade;
    }

    private boolean attackComplete() {
        Combatant combatant = (Combatant)getItem();
        combatant.setAnimation(UnitAnimation.Idle);
        return ActionComplete;
    }
}
