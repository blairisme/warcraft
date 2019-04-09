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
import com.evilbird.engine.action.framework.FeatureAction;
import com.evilbird.warcraft.item.common.resource.ResourceRequirement;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.ActionTarget.*;
import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.engine.action.common.AnimationAliasAction.setAnimation;
import static com.evilbird.engine.action.common.RepeatedAudibleAction.playRepeat;
import static com.evilbird.engine.action.common.VisibleAction.hide;
import static com.evilbird.engine.action.common.VisibleAction.show;
import static com.evilbird.engine.action.framework.DelayedAction.delay;
import static com.evilbird.engine.item.utility.ItemSuppliers.closest;
import static com.evilbird.warcraft.action.common.resource.ResourceTransferAction.transfer;
import static com.evilbird.warcraft.action.move.MoveToItemAction.move;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.*;
import static com.evilbird.warcraft.item.layer.LayerType.Tree;
import static com.evilbird.warcraft.item.unit.UnitAnimation.GatherWood;
import static com.evilbird.warcraft.item.unit.UnitAnimation.*;
import static com.evilbird.warcraft.item.unit.UnitSound.ChopWood;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;
import static com.evilbird.warcraft.item.unit.resource.ResourceType.Wood;

/**
 * Instances of this {@link Action} instruct a given Item to gather wood.
 *
 * @author Blair Butterworth
 */
//TODO: update closest to be aware of ability to move to destination
public class GatherWood extends FeatureAction
{
    private transient GatherReporter reporter;

    @Inject
    public GatherWood(GatherReporter reporter) {
        this.reporter = reporter;
        feature(GatherActions.GatherWood);
        reevaluate();
    }

    @Override
    protected void features() {
        scenario("obtain")
            .given(isAlive())
            .when(noResources(Wood))
            .then(animate(Move))
            .then(move(reporter))
            .then(animate(GatherWood))
            .then(delay(1), playRepeat(ChopWood, 1))
            .then(transfer(Target, Item, resource(), reporter))
            .then(setAnimation(Move, MoveWood), setAnimation(Idle, IdleWood))
            .then(animate(Idle))
            .withTarget(closest(Tree, getTarget()));

        scenario("deposit")
            .givenItem(isAlive())
            .whenItem(hasResources(Wood))
            .then(animate(Move))
            .then(move(reporter))
            .then(hide(), deselect(reporter))
            .then(delay(1))
            .then(transfer(Item, Player, resource(), reporter))
            .then(show(), setAnimation(Move, MoveBasic), setAnimation(Idle, IdleBasic), animate(Idle))
            .withTarget(closest(TownHall, getTarget()));
    }

    private ResourceRequirement resource() {
        return GatherActions.GatherWood;
    }
}
