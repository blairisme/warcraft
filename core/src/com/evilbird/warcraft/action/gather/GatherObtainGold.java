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
import com.evilbird.warcraft.action.common.remove.DeathAction;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.object.unit.gatherer.Gatherer;
import com.evilbird.warcraft.object.unit.resource.Resource;

import javax.inject.Inject;

import static com.evilbird.warcraft.object.common.resource.ResourceType.Gold;
import static com.evilbird.warcraft.object.unit.UnitAnimation.ExtractGold;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.IdleGold;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static com.evilbird.warcraft.object.unit.UnitAnimation.MoveGold;

/**
 * An {@link Action} that instructs a {@link Gatherer} to obtain Gold from a
 * Goldmine.
 *
 * @author Blair Butterworth
 */
public class GatherObtainGold extends GatherObtain
{
    private ItemExclusion exclusion;

    @Inject
    public GatherObtainGold(
        GatherEvents events,
        DeathAction death,
        ItemExclusion exclusion,
        ResourceTransfer resources)
    {
        super(events, death, resources);
        this.exclusion = exclusion;
        setResource(Gold);
    }

    @Override
    protected boolean initialize() {
        Gatherer gatherer = (Gatherer) getSubject();
        exclusion.disable(gatherer);

        Resource resource = (Resource)getTarget();
        resource.setAnimation(ExtractGold);
        resource.setGatherer(gatherer);

        return super.initialize();
    }

    @Override
    protected boolean complete() {
        Gatherer gatherer = (Gatherer) getSubject();
        gatherer.setAnimationAlias(IdleGold, Idle);
        gatherer.setAnimationAlias(MoveGold, Move);
        exclusion.restore(gatherer);

        Resource resource = (Resource)getTarget();
        if (resource.getGatherer() == gatherer) {
            resource.setGatherer(null);
            resource.setAnimation(Idle);
        }
        return super.complete();
    }
}
