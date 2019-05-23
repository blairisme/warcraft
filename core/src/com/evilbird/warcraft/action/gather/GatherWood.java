/*
 * Blair Butterworth (c) 2019
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
import com.evilbird.warcraft.item.common.resource.ResourceQuantum;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.ActionRecipient.*;
import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.engine.action.common.AnimationAliasAction.setAnimation;
import static com.evilbird.engine.action.common.RepeatedAudibleAction.playRepeat;
import static com.evilbird.engine.action.common.VisibleAction.hide;
import static com.evilbird.engine.action.common.VisibleAction.show;
import static com.evilbird.engine.action.framework.DelayedAction.delay;
import static com.evilbird.warcraft.action.common.transfer.TransferAction.transfer;
import static com.evilbird.warcraft.action.gather.GatherEvents.*;
import static com.evilbird.warcraft.action.move.MoveToItemAction.move;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.*;
import static com.evilbird.warcraft.item.common.query.UnitSuppliers.closest;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Wood;
import static com.evilbird.warcraft.item.layer.LayerType.Tree;
import static com.evilbird.warcraft.item.unit.UnitAnimation.GatherWood;
import static com.evilbird.warcraft.item.unit.UnitAnimation.*;
import static com.evilbird.warcraft.item.unit.UnitSound.ChopWood;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;

/**
 * Instances of this {@link Action} instruct a given {@link Item} to gather wood.
 *
 * @author Blair Butterworth
 */
public class GatherWood extends ScenarioSetAction
{
    private static final float DEPOSIT_TIME = 5;
    private static final float GATHER_TIME = 45;
    private static final ResourceQuantity GATHER_AMOUNT = ResourceQuantum.resource(Wood, 100);

    private transient GatherReporter reporter;

    @Inject
    public GatherWood(GatherReporter reporter) {
        this.reporter = reporter;
        feature(GatherActions.GatherWood);
        reevaluate();
    }

    @Override
    protected void features() {
        scenario("Gather Wood")
            .given(isAlive())
            .when(noResources(Wood))
            .then(deselect(reporter))
            .then(animate(Move))
            .then(move(reporter))
            .then(animate(GatherWood), obtainStarted(reporter, GATHER_AMOUNT))
            .then(delay(GATHER_TIME), playRepeat(ChopWood, 40, 1))
            .then(transfer(Target, Subject, GATHER_AMOUNT, reporter), obtainComplete(reporter, GATHER_AMOUNT))
            .then(setAnimation(Move, MoveWood), setAnimation(Idle, IdleWood))
            .then(animate(Idle))
            .withTarget(closest(getGatherer(), Tree, getTarget()));

        scenario("Deposit Wood")
            .givenItem(isAlive())
            .whenItem(hasResources(Wood))
            .then(animate(Move), deselect(reporter))
            .then(move(reporter))
            .then(hide(), depositStarted(reporter, GATHER_AMOUNT))
            .then(transfer(Subject, Player, GATHER_AMOUNT, reporter))
            .then(delay(DEPOSIT_TIME))
            .then(depositComplete(reporter, GATHER_AMOUNT))
            .then(show(), setAnimation(Move, MoveBasic), setAnimation(Idle, IdleBasic))
            .then(animate(Idle))
            .withTarget(closest(getGatherer(), TownHall));
    }

    public Gatherer getGatherer() {
        return (Gatherer)getItem();
    }
}
