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
import com.evilbird.engine.action.framework.ScenarioSetAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.ActionRecipient.*;
import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.engine.action.common.AnimationAliasAction.setAnimation;
import static com.evilbird.engine.action.common.DisableAction.disable;
import static com.evilbird.engine.action.common.DisableAction.enable;
import static com.evilbird.engine.action.common.VisibleAction.hide;
import static com.evilbird.engine.action.common.VisibleAction.show;
import static com.evilbird.engine.action.framework.DelayedAction.delay;
import static com.evilbird.engine.item.utility.ItemSuppliers.closest;
import static com.evilbird.warcraft.action.common.resource.ResourceTransferAction.transfer;
import static com.evilbird.warcraft.action.gather.GatherEvents.*;
import static com.evilbird.warcraft.action.move.MoveToItemAction.move;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.*;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Gold;
import static com.evilbird.warcraft.item.unit.UnitAnimation.*;
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
            .withTarget(closest(GoldMine, getTarget()));

        scenario("deposit")
            .givenItem(isAlive())
            .whenItem(hasResources(Gold))
            .then(animate(Move))
            .then(move(reporter))
            .then(hide(), disable(), deselect(reporter), depositStarted(reporter, resource()))
            .then(delay(5))
            .then(transfer(Subject, Player, resource(), reporter), depositComplete(reporter, resource()))
            .then(show(), enable(), animate(Idle), setAnimation(Move, MoveBasic))
            .withTarget(closest(TownHall, getTarget()));
    }

    private ResourceQuantity resource() {
        return GatherActions.GatherGold;
    }
}
