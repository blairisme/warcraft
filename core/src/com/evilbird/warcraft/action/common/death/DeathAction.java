/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.death;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.action.common.scenario.ScenarioSetAction;

import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.engine.action.common.AudibleAction.play;
import static com.evilbird.engine.action.common.DisableAction.disable;
import static com.evilbird.engine.action.common.ReorderAction.sendToBack;
import static com.evilbird.engine.action.framework.DelayedAction.delay;
import static com.evilbird.warcraft.action.common.remove.RemoveAction.remove;
import static com.evilbird.warcraft.action.common.transfer.TransferAction.deposit;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isBuilding;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCombatant;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCritter;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isResource;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Decompose;
import static com.evilbird.warcraft.item.unit.UnitCosts.reservedResources;
import static com.evilbird.warcraft.item.unit.UnitSound.Die;

/**
 * Instances of this {@link Action} animate and remove an item after its been
 * killed.
 *
 * @author Blair Butterworth
 */
public class DeathAction extends ScenarioSetAction
{
    private static final int DECOMPOSE_PERIOD = 30;

    private Events events;

    public DeathAction(Events events) {
        this.events = events;
    }

    public static DeathAction kill(Events events) {
        return new DeathAction(events);
    }

    @Override
    protected void features() {
        buildingDeath();
        combatantDeath();
        critterDeath();
        resourceDeath();
    }

    private void buildingDeath() {
        scenario("Building death")
            .whenItem(isBuilding())
            .then(animate(Death), deselect(events), disable(), sendToBack())
            .then(play(Die), delay(DECOMPOSE_PERIOD))
            .then(remove(events));
    }

    private void combatantDeath() {
        scenario("Combatant death")
            .whenItem(isCombatant())
            .then(animate(Death), deselect(events), disable(), sendToBack())
            .then(play(Die), delay(1))
            .then(deposit(reservedResources(getItem()), events))
            .then(animate(Decompose), delay(DECOMPOSE_PERIOD))
            .then(remove(events));
    }

    private void critterDeath() {
        scenario("Critter death")
            .whenItem(isCritter())
            .then(animate(Death), deselect(events), disable(), sendToBack())
            .then(play(Die), delay(DECOMPOSE_PERIOD))
            .then(remove(events));
    }

    private void resourceDeath() {
        scenario("Resource death")
            .whenItem(isResource())
            .then(animate(Death), deselect(events), disable(), sendToBack())
            .then(play(Die), delay(DECOMPOSE_PERIOD))
            .then(remove(events));
    }
}
