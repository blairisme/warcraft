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
    protected boolean initialize() {
        Gatherer gatherer = (Gatherer) getSubject();
        exclusion.disable(gatherer);

        OilPlatform resource = (OilPlatform)getTarget();
        resource.setAnimation(ExtractOil);
        resource.setGatherer(gatherer);

        return super.initialize();
    }

    @Override
    protected boolean complete() {
        Gatherer gatherer = (Gatherer) getSubject();
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
