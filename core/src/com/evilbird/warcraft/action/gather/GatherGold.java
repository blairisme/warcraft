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
import com.evilbird.warcraft.action.common.scenario.ScenarioSetAction;
import com.evilbird.engine.item.Item;
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
import static com.evilbird.warcraft.action.common.resource.ResourceTransferAction.transfer;
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
import static com.evilbird.warcraft.item.common.resource.ResourceType.Gold;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
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
    private transient GatherReporter reporter;

    @Inject
    public GatherGold(GatherReporter reporter) {
        this.reporter = reporter;
        feature(GatherActions.GatherGold);
        reevaluate();
    }

    @Override
    protected void features() {
        scenario("obtain")
            .given(isAlive())
            .when(noResources(Gold))
            .then(animate(Move))
            .then(move(reporter))
            .then(hide(), disable(), deselect(reporter), obtainStarted(reporter, resource()))
            .then(delay(5))
            .then(transfer(Target, Subject, resource(), reporter), obtainComplete(reporter, resource()))
            .then(show(), enable(), setAnimation(Move, MoveGold), animate(Idle))
            .withTarget(closest(getGatherer(), GoldMine, getTarget()));

        scenario("deposit")
            .givenItem(isAlive())
            .whenItem(hasResources(Gold))
            .then(animate(Move))
            .then(move(reporter))
            .then(hide(), disable(), deselect(reporter), depositStarted(reporter, resource()))
            .then(delay(5))
            .then(transfer(Subject, Player, resource(), reporter), depositComplete(reporter, resource()))
            .then(show(), enable(), animate(Idle), setAnimation(Move, MoveBasic))
            .withTarget(closest(getGatherer(), TownHall));
    }

    private ResourceQuantity resource() {
        return GatherActions.GatherGold;
    }

    public Gatherer getGatherer() {
        return (Gatherer)getItem();
    }
}
