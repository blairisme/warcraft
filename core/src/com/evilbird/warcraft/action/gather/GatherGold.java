/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.scenario.ScenarioSetAction;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.ActionRecipient.Player;
import static com.evilbird.engine.action.common.ActionRecipient.Subject;
import static com.evilbird.engine.action.common.ActionRecipient.Target;
import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.engine.action.common.AnimationAliasAction.setAnimation;
import static com.evilbird.engine.action.common.DisableAction.disable;
import static com.evilbird.engine.action.common.DisableAction.enable;
import static com.evilbird.engine.action.common.VisibleAction.hide;
import static com.evilbird.engine.action.common.VisibleAction.show;
import static com.evilbird.engine.action.framework.DelayedAction.delay;
import static com.evilbird.warcraft.action.common.transfer.TransferAction.transfer;
import static com.evilbird.warcraft.action.gather.GatherEvents.depositComplete;
import static com.evilbird.warcraft.action.gather.GatherEvents.depositStarted;
import static com.evilbird.warcraft.action.gather.GatherEvents.obtainComplete;
import static com.evilbird.warcraft.action.gather.GatherEvents.obtainStarted;
import static com.evilbird.warcraft.action.move.MoveToItemAction.move;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.hasResources;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.noResources;
import static com.evilbird.warcraft.item.common.query.UnitSuppliers.closest;
import static com.evilbird.warcraft.item.common.resource.ResourceQuantum.resource;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Gold;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.IdleBasic;
import static com.evilbird.warcraft.item.unit.UnitAnimation.IdleGold;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;
import static com.evilbird.warcraft.item.unit.UnitAnimation.MoveBasic;
import static com.evilbird.warcraft.item.unit.UnitAnimation.MoveGold;
import static com.evilbird.warcraft.item.unit.UnitType.GoldMine;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;

/**
 * Instances of this {@link Action} instruct an {@link Item} to gather gold.
 *
 * @author Blair Butterworth
 */
public class GatherGold extends ScenarioSetAction
{
    private static final float DEPOSIT_TIME = 5;
    private static final float GATHER_TIME = 5;
    private static final ResourceQuantity GATHER_AMOUNT =  resource(Gold, 100);

    private transient GatherReporter reporter;

    @Inject
    public GatherGold(GatherReporter reporter) {
        this.reporter = reporter;
        feature(GatherActions.GatherGold);
        reevaluate();
    }

    @Override
    protected void features() {
        gatherFeature();
        depositFeature();
    }

    private void gatherFeature() {
        scenario("Gather Gold")
            .given(isAlive())
            .when(noResources(Gold))
            .then(deselect(reporter))
            .then(animate(Move))
            .then(move(reporter))
            .then(hide(), disable(), obtainStarted(reporter, GATHER_AMOUNT))
            .then(delay(GATHER_TIME))
            .then(transfer(Target, Subject, GATHER_AMOUNT, reporter), obtainComplete(reporter, GATHER_AMOUNT))
            .then(show(), enable(), setAnimation(Move, MoveGold), setAnimation(Idle, IdleGold))
            .then(animate(Idle))
            .withTarget(closest(getGatherer(), GoldMine, getTarget()));
    }

    private void depositFeature() {
        scenario("Deposit Wood")
            .givenItem(isAlive())
            .whenItem(hasResources(Gold))
            .then(animate(Move), deselect(reporter))
            .then(move(reporter))
            .then(hide(), disable(), depositStarted(reporter, GATHER_AMOUNT))
            .then(transfer(Subject, Player, GATHER_AMOUNT, reporter))
            .then(delay(DEPOSIT_TIME))
            .then(depositComplete(reporter, GATHER_AMOUNT))
            .then(show(), enable(), setAnimation(Idle, IdleBasic), setAnimation(Move, MoveBasic))
            .then(animate(Idle))
            .withTarget(closest(getGatherer(), TownHall));
    }

    public Gatherer getGatherer() {
        return (Gatherer)getItem();
    }
}
