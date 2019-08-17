/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.item.common.query.UnitPredicates;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import java.util.Collection;

import static com.evilbird.engine.common.collection.CollectionUtils.convert;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isAttacker;

/**
 * Synchronises the attacker locations and line of sight contained in an
 * {@link AttackGraph} based on events stored in the global {@link EventQueue}.
 *
 * @author Blair Butterworth
 */
public class AttackGraphUpdater
{
    private AttackGraph graph;
    private EventQueue events;

    public AttackGraphUpdater(AttackGraph graph, EventQueue events) {
        this.events = events;
        this.graph = graph;
    }

    public void initialize(ItemRoot root) {
        Collection<Item> items = root.findAll(UnitPredicates.isAttacker());
        Collection<Combatant> attackers = convert(items, a -> (Combatant)a);
        graph.addAttackers(attackers);
    }

    public void update() {
        for (CreateEvent event: events.getEvents(CreateEvent.class)) {
            Item subject = event.getSubject();
            if (isAttacker(subject)) {
                graph.addAttacker((Combatant)subject);
            }
        }
        for (RemoveEvent event: events.getEvents(RemoveEvent.class)) {
            Item subject = event.getSubject();
            if (isAttacker(subject)) {
                graph.removeAttacker((Combatant)subject);
            }
        }
        for (MoveEvent event: events.getEvents(MoveEvent.class)) {
            Item subject = event.getSubject();
            if (isAttacker(subject)){
                graph.updateAttacker((Combatant)subject);
            }
        }
    }
}
