/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.warcraft.action.gather.GatherEvents.notifyGatherCancelled;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Instances of this class stop a given {@link Item} from gathering, retaining
 * resources from the partially completed gathering process.
 *
 * @author Blair Butterworth
 */
public class GatherCancel extends BasicAction
{
    private Events events;

    @Inject
    public GatherCancel(EventQueue events) {
        this.events = events;
    }

    @Override
    public boolean act(float delta) {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setAnimation(Idle);
        notifyGatherCancelled(events, gatherer);
        return ActionComplete;
    }
}
