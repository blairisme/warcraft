/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.TransitionAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.gather.GatherLocations.closestDepot;
import static com.evilbird.warcraft.action.gather.GatherLocations.closestResource;
import static com.evilbird.warcraft.data.resource.ResourceType.Gold;
import static com.evilbird.warcraft.object.common.query.UnitOperations.hasResources;
import static com.evilbird.warcraft.object.unit.UnitType.GoldMine;

/**
 * Instances of this {@link Action} instruct an {@link GameObject} to gather gold.
 *
 * @author Blair Butterworth
 */
public class GatherGold extends TransitionAction
{
    private transient Action obtain;
    private transient Action deposit;

    @Inject
    public GatherGold(
        GatherDeposit deposit,
        GatherObtainGold obtain,
        MoveToItemAction moveToDepot,
        MoveToItemAction moveToResource)
    {
        setIdentifier(GatherActions.GatherGold);
        obtain.setResource(Gold);
        deposit.setResource(Gold);
        this.deposit = add(new SequenceAction(moveToDepot, deposit));
        this.obtain = add(new SequenceAction(moveToResource, obtain));
    }

    @Override
    protected Action nextAction(Action previous) {
        return nextAction((Gatherer)getSubject(), getTarget());
    }

    private Action nextAction(Gatherer gatherer, GameObject target) {
        if (hasResources(gatherer, Gold)) {
            return getDepositAction(gatherer);
        } else {
            return getObtainAction(gatherer, target);
        }
    }

    private Action getObtainAction(Gatherer gatherer, GameObject target) {
        GameObject resource = closestResource(gatherer, target, GoldMine);
        if (resource != null) {
            obtain.setTarget(resource);
            return obtain;
        } else {
            //setFailed("Unable to locate resource of type gold");
            return null;
        }
    }

    private Action getDepositAction(Gatherer gatherer) {
        GameObject depot = closestDepot(gatherer, Gold);
        if (depot != null) {
            deposit.setTarget(depot);
            return deposit;
        } else {
            //setFailed("Unable to locate depot for gold");
            return null;
        }
    }
}
