/*
 * Copyright (c) 2019, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.StateTransitionAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.item.common.query.UnitOperations.findClosest;
import static com.evilbird.warcraft.item.common.query.UnitOperations.hasResources;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCorporeal;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isDepotFor;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Oil;
import static com.evilbird.warcraft.item.unit.UnitType.OilPlatform;
import static com.evilbird.warcraft.item.unit.UnitType.OilRig;

/**
 * Instances of this {@link Action} instruct an {@link Item} to gather gold.
 *
 * @author Blair Butterworth
 */
public class GatherOil extends StateTransitionAction
{
    private static final float DEPOSIT_TIME = 5;
    private static final float GATHER_TIME = 5;
    private static final ResourceQuantity RESOURCE = new ResourceQuantity(Oil, 100);

    private Action gather;
    private Action deposit;

    @Inject
    public GatherOil(
        GatherDeposit deposit,
        GatherObtainOil gather,
        MoveToItemAction moveToDepot,
        MoveToItemAction moveToResource)
    {
        this.deposit = add(new SequenceAction(moveToDepot, deposit));
        this.gather = add(new SequenceAction(moveToResource, gather));

        gather.setResource(RESOURCE);
        gather.setDuration(GATHER_TIME);

        deposit.setResource(RESOURCE);
        deposit.setDuration(DEPOSIT_TIME);
    }

    @Override
    protected Action nextAction(Action previous) {
        return nextAction((Gatherer)getItem(), getTarget());
    }

    private Action nextAction(Gatherer gatherer, Item target) {
        if (hasResources(gatherer, Oil)) {
            deposit.setTarget(getNearestDepot(gatherer));
            return deposit;
        } else {
            gather.setTarget(getNearestResource(gatherer, target));
            return gather;
        }
    }

    private Item getNearestDepot(Gatherer gatherer) {
        return findClosest(gatherer, both(isCorporeal(), isDepotFor(Oil)));
    }

    private Item getNearestResource(Gatherer gatherer, Item target) {
        WarcraftFaction faction = UnitOperations.getFaction(gatherer);
        UnitType oilExtractor = faction == Human ? OilPlatform : OilRig;
        return findClosest(gatherer, target, oilExtractor);
    }
}