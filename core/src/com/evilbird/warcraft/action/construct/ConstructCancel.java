/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.scenario.ScenarioAction;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.ActionRecipient.Subject;
import static com.evilbird.engine.action.common.ActionRecipient.Target;
import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.engine.action.common.AudibleAction.play;
import static com.evilbird.engine.action.common.DisableAction.disable;
import static com.evilbird.engine.action.common.DisableAction.enable;
import static com.evilbird.engine.action.common.VisibleAction.show;
import static com.evilbird.engine.action.framework.ClearAction.clear;
import static com.evilbird.engine.action.framework.DelayedAction.delay;
import static com.evilbird.warcraft.action.common.remove.RemoveAction.remove;
import static com.evilbird.warcraft.action.common.resource.ResourceTransferAction.deposit;
import static com.evilbird.warcraft.action.construct.ConstructAction.stopConstructing;
import static com.evilbird.warcraft.action.construct.ConstructEvents.constructCancelled;
import static com.evilbird.warcraft.action.move.MoveAdjacent.moveAdjacent;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isConstructing;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Dead;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitCosts.costOf;
import static com.evilbird.warcraft.item.unit.UnitSound.Die;

/**
 * Instances of this class stop the construction of a building.
 *
 * @author Blair Butterworth
 */
public class ConstructCancel extends ScenarioAction<ConstructActions>
{
    private ConstructReporter reporter;

    /**
     * Constructs a new instance of this class given a {@link ConstructReporter}
     * used to report the transfer of resources involved in the refund for the
     * partially complete train operation.
     *
     * @param reporter  a {@code TrainReporter} instance. This parameter
     *                  cannot be {@code null}.
     */
    @Inject
    public ConstructCancel(ConstructReporter reporter) {
        this.reporter = reporter;
    }

    @Override
    protected void steps(ConstructActions action) {
        scenario(action);
        steps(action.getUnitType());
    }

    private void steps(UnitType building) {
        setTarget(getBuilder());
        given(isConstructing());
        then(stopConstructing(), disable(), deselect(reporter), animate(Dead), play(Die));
        then(clear(Target), show(Target), enable(Target), animate(Target, Idle), moveAdjacent(Target, Subject));
        then(deposit(costOf(building), reporter), constructCancelled(reporter), delay(10));
        then(remove(reporter));
    }

    private Item getBuilder() {
        Building building = (Building)getItem();
        return building.getConstructor();
    }
}
