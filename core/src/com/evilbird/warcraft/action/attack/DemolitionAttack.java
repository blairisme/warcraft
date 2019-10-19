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
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.action.common.remove.RemoveEvents;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.select.SelectEvents;
import com.evilbird.warcraft.item.common.state.OffensiveObject;
import com.evilbird.warcraft.item.common.state.PerishableObject;
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
    protected boolean attackRequired(OffensiveObject attacker, PerishableObject target) {
        return !attackAction.isComplete() && super.attackRequired(attacker, target);
    }

    @Override
    protected boolean attackTarget(float time, OffensiveObject attacker, PerishableObject target) {
        disableAttacker(attacker);
        return super.attackTarget(time, attacker, target);
    }

    protected void disableAttacker(OffensiveObject attacker) {
        Combatant combatant = (Combatant)attacker;
        if (combatant.getSelectable()) {
            combatant.setSelected(false);
            combatant.setSelectable(false);
            selectEvents.notifySelected(combatant, false);
        }
    }

    @Override
    protected boolean killRequired(OffensiveObject attacker, PerishableObject target) {
        return attackAction.isComplete();
    }

    @Override
    protected boolean killTarget(OffensiveObject attacker, PerishableObject target) {
        killAttacker(attacker);
        killTarget(target);
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
}
