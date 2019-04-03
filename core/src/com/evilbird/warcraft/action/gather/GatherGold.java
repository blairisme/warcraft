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
import com.evilbird.engine.action.framework.FeatureAction;
import com.evilbird.warcraft.item.common.resource.ResourceRequirement;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.ActionTarget.*;
import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.engine.action.common.VisibleAction.hide;
import static com.evilbird.engine.action.common.VisibleAction.show;
import static com.evilbird.engine.action.framework.DelayedAction.delay;
import static com.evilbird.engine.item.utility.ItemSuppliers.closest;
import static com.evilbird.warcraft.action.common.animation.AnimationAliasAction.setAnimation;
import static com.evilbird.warcraft.action.common.resource.ResourceTransferAction.transfer;
import static com.evilbird.warcraft.action.move.MoveToItemAction.move;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.*;
import static com.evilbird.warcraft.item.unit.UnitAnimation.*;
import static com.evilbird.warcraft.item.unit.UnitType.GoldMine;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;
import static com.evilbird.warcraft.item.unit.resource.ResourceType.Gold;

/**
 * Instances of this {@link Action} instruct an Item to gather gold.
 *
 * @author Blair Butterworth
 */
public class GatherGold extends FeatureAction
{
    private GatherReporter reporter;

    @Inject
    public GatherGold(GatherReporter reporter) {
        this.reporter = reporter;
        feature(GatherActions.GatherGold);
        repeats();
    }

    @Override
    protected void features() {
        scenario("obtain")
            .given(isAlive())
            .when(noResources(Gold))
            .then(animate(Move))
            .then(move(reporter))
            .then(hide(), deselect(reporter))
            .then(delay(5))
            .then(transfer(Target, Item, resource(), reporter))
            .then(show(), setAnimation(Move, MoveGold), animate(Idle))
            .withTarget(closest(GoldMine, getTarget()));

        scenario("deposit")
            .givenItem(isAlive())
            .whenItem(hasResources(Gold))
            .then(animate(Move))
            .then(move(reporter))
            .then(hide(), deselect(reporter))
            .then(delay(5))
            .then(transfer(Item, Player, resource(), reporter))
            .then(show(), animate(Idle), setAnimation(Move, MoveBasic))
            .withTarget(closest(TownHall, getTarget()));
    }

    private ResourceRequirement resource() {
        return GatherActions.GatherGold;
    }
}
