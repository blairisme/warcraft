/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.action.common.remove.RemoveEvents;
import com.evilbird.warcraft.action.common.remove.DeathAction;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.select.SelectEvents;
import com.evilbird.warcraft.item.common.capability.OffensiveObject;
import com.evilbird.warcraft.item.common.capability.PerishableObject;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.item.utility.ItemOperations.assignIfAbsent;

/**
 * An {@link Action} that causes a given {@link OffensiveObject demolition
 * combatant} to attack a {@link PerishableObject} after first moving adjacent
 * to it and detonating itself, dealing all damage instantly.
 *
 * @author Blair Butterworth
 */
public class DemolitionAttack extends AttackSequence
{
    private transient boolean attackStarted;
    private transient SelectEvents selectEvents;
    private transient RemoveEvents removeEvents;
    private transient InstantAttack attackAction;
    private transient DeathAction deathAction;

    @Inject
    public DemolitionAttack(
        AttackEvents attackEvents,
        SelectEvents selectEvents,
        RemoveEvents removeEvents,
        MoveToItemAction moveAction,
        InstantAttack attackAction,
        DeathAction deathAction)
    {
        super(attackEvents, moveAction, attackAction, deathAction);
        this.selectEvents = selectEvents;
        this.removeEvents = removeEvents;
        this.attackAction = attackAction;
        this.deathAction = deathAction;
    }

    @Override
    public void reset() {
        super.reset();
        attackStarted = false;
    }

    @Override
    public void restart() {
        super.restart();
        attackStarted = false;
    }

    @Override
    protected boolean attackFailed(OffensiveObject attacker, PerishableObject target) {
        return !attackStarted && super.attackFailed(attacker, target);
    }

    @Override
    protected boolean attackRequired(OffensiveObject attacker, PerishableObject target) {
        return !attackStarted ? super.attackRequired(attacker, target) : !attackAction.isComplete();
    }

    @Override
    protected boolean attack(float time, OffensiveObject attacker, PerishableObject target) {
        boolean result = super.attack(time, attacker, target);
        if (!attackStarted) {
            attackStarted = true;
            disableAttacker(attacker);
            killTarget(target);
        }
        return result;
    }

    @Override
    protected boolean killRequired(OffensiveObject attacker, PerishableObject target) {
        return attackAction.isComplete();
    }

    @Override
    protected boolean kill(OffensiveObject attacker, PerishableObject target) {
        killAttacker(attacker);
        return ActionComplete;
    }

    private void killTarget(PerishableObject target) {
        if (target.getHealth() == 0) {
            assignIfAbsent(target, deathAction);
        }
    }

    private void killAttacker(OffensiveObject attacker) {
        ItemGroup parent = attacker.getParent();
        parent.removeItem(attacker);
        removeEvents.notifyRemove(attacker);
    }

    protected void disableAttacker(OffensiveObject attacker) {
        Combatant combatant = (Combatant)attacker;
        if (combatant.getSelectable()) {
            combatant.setSelected(false);
            combatant.setSelectable(false);
            combatant.setTouchable(Touchable.disabled);
            selectEvents.notifySelected(combatant, false);
        }
    }
}
