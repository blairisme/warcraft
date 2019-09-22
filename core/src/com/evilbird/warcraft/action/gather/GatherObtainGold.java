/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.action.common.exclusion.Exclusion;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;
import com.evilbird.warcraft.item.unit.resource.Resource;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.unit.UnitAnimation.Gathering;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.IdleGold;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;
import static com.evilbird.warcraft.item.unit.UnitAnimation.MoveGold;

public class GatherObtainGold extends GatherObtain
{
    @Inject
    public GatherObtainGold(Events events, DeathAction death) {
        super(events, death);
    }

    @Override
    protected boolean initialize() {
        Gatherer gatherer = (Gatherer)getItem();
        Exclusion.disable(gatherer, events);

        Resource resource = (Resource)getTarget();
        resource.setAnimation(Gathering);

        return super.initialize();
    }

    @Override
    protected boolean complete() {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setAnimationAlias(IdleGold, Idle);
        gatherer.setAnimationAlias(MoveGold, Move);
        Exclusion.restore(gatherer);

        Resource resource = (Resource)getTarget();
        resource.setAnimation(Idle);

        return super.complete();
    }
}
