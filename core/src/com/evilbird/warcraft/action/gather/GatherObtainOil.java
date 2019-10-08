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
import com.evilbird.warcraft.item.unit.building.ResourceExtractor;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.resource.ResourceType.Oil;
import static com.evilbird.warcraft.item.unit.UnitAnimation.ExtractOil;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.IdleOil;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;
import static com.evilbird.warcraft.item.unit.UnitAnimation.MoveOil;

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
        Gatherer gatherer = (Gatherer)getItem();
        exclusion.disable(gatherer);

        ResourceExtractor resource = (ResourceExtractor)getTarget();
        resource.setAnimation(ExtractOil);
        resource.setAssociatedItem(gatherer);

        return super.initialize();
    }

    @Override
    protected boolean complete() {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setAnimationAlias(IdleOil, Idle);
        gatherer.setAnimationAlias(MoveOil, Move);
        exclusion.restore(gatherer);

        ResourceExtractor resource = (ResourceExtractor)getTarget();
        if (resource.getAssociatedItem() == gatherer) {
            resource.setAssociatedItem(null);
            resource.setAnimation(Idle);
        }
        return super.complete();
    }
}
