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
import com.evilbird.engine.action.framework.StateTransitionAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.data.resource.ResourceType.Oil;
import static com.evilbird.warcraft.object.common.query.UnitOperations.findClosest;
import static com.evilbird.warcraft.object.common.query.UnitOperations.hasResources;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isCorporeal;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isDepotFor;
import static com.evilbird.warcraft.object.unit.UnitType.OilPlatform;
import static com.evilbird.warcraft.object.unit.UnitType.OilRig;

/**
 * Instances of this {@link Action} instruct an {@link GameObject} to gather gold.
 *
 * @author Blair Butterworth
 */
public class GatherOil extends StateTransitionAction
{
    private transient Action gather;
    private transient Action deposit;

    @Inject
    public GatherOil(
        GatherDeposit deposit,
        GatherObtainOil gather,
        MoveToItemAction moveToDepot,
        MoveToItemAction moveToResource)
    {
        setIdentifier(GatherActions.GatherOil);
        gather.setResource(Oil);
        deposit.setResource(Oil);
        this.deposit = add(new SequenceAction(moveToDepot, deposit));
        this.gather = add(new SequenceAction(moveToResource, gather));
    }

    @Override
    protected Action nextAction(Action previous) {
        return nextAction((Gatherer) getSubject(), getTarget());
    }

    private Action nextAction(Gatherer gatherer, GameObject target) {
        if (hasResources(gatherer, Oil)) {
            deposit.setTarget(getNearestDepot(gatherer));
            return deposit;
        } else {
            gather.setTarget(getNearestResource(gatherer, target));
            return gather;
        }
    }

    private GameObject getNearestDepot(Gatherer gatherer) {
        return findClosest(gatherer, both(isCorporeal(), isDepotFor(Oil)));
    }

    private GameObject getNearestResource(Gatherer gatherer, GameObject target) {
        UnitType type = (UnitType)gatherer.getType();
        WarcraftFaction faction =  type.getFaction();
        UnitType extractor = faction == Human ? OilPlatform : OilRig;
        return findClosest(gatherer, target, extractor);
    }
}