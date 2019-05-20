/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.action.common.remove.RemoveObserver;
import com.evilbird.warcraft.action.common.transfer.TransferEvent;
import com.evilbird.warcraft.action.common.transfer.TransferObserver;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.action.move.MoveObserver;
import com.evilbird.warcraft.action.select.SelectEvent;
import com.evilbird.warcraft.action.select.SelectObserver;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * Instances of this class generate {@link AttackEvent AttackEvents} based on
 * the operation of attack actions.
 *
 * @author Blair Butterworth
 */
public class AttackReporter implements
        AttackObserver, MoveObserver, SelectObserver, RemoveObserver, TransferObserver
{
    private EventQueue events;

    @Inject
    public AttackReporter(EventQueue events) {
        this.events = events;
    }

    @Override
    public void onAttackStarted(Combatant attacker, Item target) {
        events.add(new AttackEvent(attacker, target, AttackStatus.Started));
    }

    @Override
    public void onAttackCompleted(Combatant attacker, Item target) {
        events.add(new AttackEvent(attacker, target, AttackStatus.Complete));
    }

    @Override
    public void onAttackCancelled(Combatant attacker, Item target) {
        events.add(new AttackEvent(attacker, target, AttackStatus.Cancelled));
    }

    @Override
    public void onMove(Item subject, ItemNode location) {
        events.add(new MoveEvent(subject, location));
    }

    @Override
    public void onSelect(Item item, boolean selected) {
        events.add(new SelectEvent(item, selected));
    }

    @Override
    public void onRemove(Item removed) {
        events.add(new RemoveEvent(removed));
    }

    @Override
    public void onTransfer(ResourceContainer recipient, ResourceType resource, float oldValue, float newValue) {
        events.add(new TransferEvent(recipient, resource, oldValue, newValue));
    }
}
