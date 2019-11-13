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
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.unit.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;

/**
 * Instances of this class stop a given {@link GameObject} from gathering, retaining
 * resources from the partially completed gathering process.
 *
 * @author Blair Butterworth
 */
public class GatherCancel extends BasicAction
{
    private transient GatherEvents events;

    @Inject
    public GatherCancel(GatherEvents events) {
        this.events = events;
    }

    @Override
    public boolean act(float delta) {
        Gatherer gatherer = (Gatherer) getSubject();
        gatherer.setAnimation(Idle);
        events.notifyGatherCancelled(gatherer);
        return ActionComplete;
    }
}
