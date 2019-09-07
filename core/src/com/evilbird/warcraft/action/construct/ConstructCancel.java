/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.warcraft.action.common.death.DeathAction;
import com.evilbird.warcraft.action.common.scenario.ScenarioSetAction;
import com.evilbird.warcraft.item.unit.UnitCosts;
import com.evilbird.warcraft.item.unit.UnitType;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.ActionRecipient.Target;
import static com.evilbird.engine.action.framework.ClearAction.clear;
import static com.evilbird.engine.common.function.Predicates.not;
import static com.evilbird.engine.item.utility.ItemPredicates.isVisible;
import static com.evilbird.warcraft.action.common.associate.AssociateAction.unassociate;
import static com.evilbird.warcraft.action.common.exclusion.ExcludeActions.restore;
import static com.evilbird.warcraft.action.common.transfer.TransferAction.deposit;
import static com.evilbird.warcraft.action.construct.ConstructAction.stopConstructing;
import static com.evilbird.warcraft.action.construct.ConstructEvents.constructCancelled;
import static com.evilbird.warcraft.action.move.MoveAdjacent.moveAdjacentSubject;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isConstructing;

/**
 * Instances of this class stop the construction of a building.
 *
 * @author Blair Butterworth
 */
public class ConstructCancel extends ScenarioSetAction
{
    private transient EventQueue events;
    private transient DeathAction kill;

    /**
     * Creates a new instance of this class given a {@link EventQueue}
     * used to report the transferAll of resources involved in the refund for the
     * partially complete train operation.
     *
     * @param events    a {@code EventQueue} instance. This parameter
     *                  cannot be {@code null}.
     * @param death     an action that will be used to destroy the construction
     *                  site.
     */
    @Inject
    public ConstructCancel(EventQueue events, DeathAction death) {
        this.events = events;
        this.kill = death;
    }

    @Override
    protected void features() {
        ConstructActions actions = (ConstructActions)getIdentifier();
        features(actions.getProduct());
    }

    private void features(UnitType building) {
        cancelPreConstruction(building);
        cancelDuringConstruction(building);
    }

    private void cancelPreConstruction(UnitType building) {
        scenario("Cancel pre-construction")
            .whenItem(isConstructing())
            .whenTarget(isVisible())
            .then(stopConstructing(), unassociate())
            .then(restore(Target))
            .then(deposit(UnitCosts.cost(building), events))
            .then(constructCancelled(events))
            .then(clear(Target))
            .then(kill);
    }

    private void cancelDuringConstruction(UnitType building) {
        scenario("Cancel during construction")
            .whenItem(isConstructing())
            .whenTarget(not(isVisible()))
            .then(stopConstructing(), unassociate())
            .then(moveAdjacentSubject())
            .then(restore(Target))
            .then(deposit(UnitCosts.cost(building), events))
            .then(constructCancelled(events))
            .then(clear(Target))
            .then(kill);
    }
}
