/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

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

public class DemolitionAttack extends AttackSequence
{
    private SelectEvents selectEvents;
    private RemoveEvents removeEvents;

    @Inject
    public DemolitionAttack(
        AttackEvents events,
        SelectEvents selectEvents,
        RemoveEvents removeEvents,
        MoveToItemAction move,
        ProximityAttack attack,
        DeathAction death)
    {
        super(events, move, attack, death);
        this.selectEvents = selectEvents;
        this.removeEvents = removeEvents;
    }

    @Override
    protected boolean attackTarget(float time, OffensiveObject attacker, PerishableObject target) {
        Combatant combatant = (Combatant)attacker;
        if (combatant.getSelectable()) {
            combatant.setSelected(false);
            combatant.setSelectable(false);
            selectEvents.notifySelected(combatant, false);
        }
        return super.attackTarget(time, attacker, target);
    }

    @Override
    protected boolean killTarget(OffensiveObject attacker, PerishableObject target) {
        ItemGroup parent = attacker.getParent();
        parent.removeItem(attacker);
        removeEvents.notifyRemove(attacker);
        return ActionComplete;
    }
}
