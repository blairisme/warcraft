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

import javax.inject.Inject;

import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.IdleOil;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;
import static com.evilbird.warcraft.item.unit.UnitAnimation.MoveOil;

public class GatherObtainOil extends GatherObtain
{
    @Inject
    public GatherObtainOil(Events events, DeathAction death) {
        super(events, death);
    }

    @Override
    protected boolean initialize() {
        Gatherer gatherer = (Gatherer)getItem();
        Exclusion.disable(gatherer, events);

//        ResourceExtractor resource = (ResourceExtractor)getTarget();
//        resource.setAnimation(Gathering);

        return super.initialize();
    }

    @Override
    protected boolean complete() {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setAnimationAlias(IdleOil, Idle);
        gatherer.setAnimationAlias(MoveOil, Move);
        Exclusion.restore(gatherer);

//        ResourceExtractor resource = (ResourceExtractor)getTarget();
//        resource.setAnimation(Idle);

        return super.complete();
    }
}
