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
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.warcraft.item.common.query.UnitOperations.findClosest;
import static com.evilbird.warcraft.item.common.query.UnitOperations.hasResources;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCorporeal;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isDepotFor;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Gold;
import static com.evilbird.warcraft.item.unit.UnitType.GoldMine;

/**
 * Instances of this {@link Action} instruct an {@link Item} to gather gold.
 *
 * @author Blair Butterworth
 */
public class GatherGold extends StateTransitionAction
{
    private transient Action gather;
    private transient Action deposit;

    @Inject
    public GatherGold(
        GatherDeposit deposit,
        GatherObtainGold gather,
        MoveToItemAction moveToDepot,
        MoveToItemAction moveToResource)
    {
        setIdentifier(GatherActions.GatherGold);
        gather.setResource(Gold);
        deposit.setResource(Gold);
        this.deposit = add(new SequenceAction(moveToDepot, deposit));
        this.gather = add(new SequenceAction(moveToResource, gather));
    }

    @Override
    protected Action nextAction(Action previous) {
        return nextAction((Gatherer)getItem(), getTarget());
    }

    private Action nextAction(Gatherer gatherer, Item target) {
        
        if (hasResources(gatherer, Gold)) {
            deposit.setTarget(getNearestDepot(gatherer));
            return deposit;
        } else {
            gather.setTarget(getNearestResource(gatherer, target));
            return gather;
        }
    }

    private Item getNearestDepot(Gatherer gatherer) {
        return findClosest(gatherer, both(isCorporeal(), isDepotFor(Gold)));
    }

    private Item getNearestResource(Gatherer gatherer, Item target) {
        return findClosest(gatherer, target, GoldMine);
    }
}
