/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionResult;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.object.unit.building.OilPlatform;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.warcraft.data.resource.ResourceType.Oil;
import static com.evilbird.warcraft.object.unit.UnitAnimation.ExtractOil;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.IdleOil;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static com.evilbird.warcraft.object.unit.UnitAnimation.MoveOil;

/**
 * An {@link Action} that instructs a {@link Gatherer} to obtain Oil from a
 * oil patch.
 *
 * @author Blair Butterworth
 */
public class GatherObtainOil extends GatherObtain
{
    private ItemExclusion exclusion;

    @Inject
    public GatherObtainOil(
        GatherEvents events,
        DeathAction death,
        ItemExclusion exclusion,
        ResourceTransfer resources)
    {
        super(events, death, resources);
        this.exclusion = exclusion;
        setResource(Oil);
    }

    @Override
    protected ActionResult initialize() {
        Gatherer gatherer = (Gatherer)getSubject();
        exclusion.disable(gatherer);

        OilPlatform resource = (OilPlatform)getTarget();
        resource.setAnimation(ExtractOil);
        resource.setGatherer(gatherer);

        return super.initialize();
    }

    @Override
    protected ActionResult complete() {
        Gatherer gatherer = (Gatherer)getSubject();
        gatherer.setAnimationAlias(IdleOil, Idle);
        gatherer.setAnimationAlias(MoveOil, Move);
        exclusion.restore(gatherer);

        OilPlatform resource = (OilPlatform)getTarget();
        if (resource.getGatherer() == gatherer) {
            resource.setGatherer(null);
            resource.setAnimation(Idle);
        }
        return super.complete();
    }
}
