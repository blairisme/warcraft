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
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.scenario.ScenarioSetAction;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.common.resource.ResourceQuantum;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.ActionRecipient.Player;
import static com.evilbird.engine.action.common.ActionRecipient.Subject;
import static com.evilbird.engine.action.common.ActionRecipient.Target;
import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.engine.action.common.AnimationAliasAction.setAnimation;
import static com.evilbird.engine.action.common.DirectionAction.reorient;
import static com.evilbird.engine.action.common.RepeatedAudibleAction.playRepeat;
import static com.evilbird.engine.action.common.VisibleAction.hide;
import static com.evilbird.engine.action.common.VisibleAction.show;
import static com.evilbird.engine.action.framework.DelayedAction.delay;
import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.warcraft.action.common.transfer.TransferAction.transferAll;
import static com.evilbird.warcraft.action.gather.GatherEvents.depositComplete;
import static com.evilbird.warcraft.action.gather.GatherEvents.depositStarted;
import static com.evilbird.warcraft.action.gather.GatherEvents.obtainComplete;
import static com.evilbird.warcraft.action.gather.GatherEvents.obtainStarted;
import static com.evilbird.warcraft.action.move.MoveToItemAction.move;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.hasResources;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCorporeal;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isDepotFor;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.noResources;
import static com.evilbird.warcraft.item.common.query.UnitSuppliers.closest;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Wood;
import static com.evilbird.warcraft.item.layer.LayerType.Tree;
import static com.evilbird.warcraft.item.unit.UnitAnimation.GatherWood;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.IdleBasic;
import static com.evilbird.warcraft.item.unit.UnitAnimation.IdleWood;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;
import static com.evilbird.warcraft.item.unit.UnitAnimation.MoveBasic;
import static com.evilbird.warcraft.item.unit.UnitAnimation.MoveWood;
import static com.evilbird.warcraft.item.unit.UnitSound.ChopWood;

/**
 * Instances of this {@link Action} instruct a given {@link Item} to gather wood.
 *
 * @author Blair Butterworth
 */
public class GatherWood extends ScenarioSetAction
{
    private static final float DEPOSIT_TIME = 5;
    private static final float GATHER_TIME = 2;//45;
    private static final ResourceQuantity GATHER_AMOUNT = ResourceQuantum.resource(Wood, 100);

    private transient Events events;

    @Inject
    public GatherWood(EventQueue events) {
        this.events = events;
        feature(GatherActions.GatherWood);
        reevaluate();
    }

    @Override
    protected void features() {
        gatherFeature();
        obtainFeature();
    }

    private void gatherFeature() {
        scenario("Gather Wood")
            .given(isAlive())
            .when(noResources(Wood))
            .then(deselect(events))
            .then(animate(Move))
            .then(move(events))
            .then(reorient())
            .then(animate(GatherWood), obtainStarted(events, GATHER_AMOUNT))
            .then(delay(GATHER_TIME), playRepeat(ChopWood, 40, 1))
            .then(transferAll(Target, Subject, GATHER_AMOUNT, events), obtainComplete(events, GATHER_AMOUNT))
            .then(setAnimation(Move, MoveWood), setAnimation(Idle, IdleWood))
            .then(animate(Idle))
            .withTarget(closest(getGatherer(), Tree, getTarget()));
    }

    private void obtainFeature() {
        scenario("Deposit Wood")
            .givenItem(isAlive())
            .whenItem(hasResources(Wood))
            .then(animate(Move), deselect(events))
            .then(move(events))
            .then(hide(), depositStarted(events, GATHER_AMOUNT))
            .then(transferAll(Subject, Player, GATHER_AMOUNT, events))
            .then(delay(DEPOSIT_TIME))
            .then(depositComplete(events, GATHER_AMOUNT))
            .then(show(), setAnimation(Move, MoveBasic), setAnimation(Idle, IdleBasic))
            .then(animate(Idle))
            .withTarget(closest(getGatherer(), both(isCorporeal(), isDepotFor(Wood))));
    }

    public Gatherer getGatherer() {
        return (Gatherer)getItem();
    }
}
