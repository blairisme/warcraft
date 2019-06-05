/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.LambdaAction;
import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Helper class for generating construction events.
 *
 * @author Blair Butterworth
 */
public class ConstructEvents
{
    private ConstructEvents() {
    }

    public static Action constructStarted(Events events) {
        return new LambdaAction((builder, building) ->
            events.add(new ConstructEvent(builder, (Building)building, ConstructStatus.Started)));
    }

    public static Action constructCompleted(Events events) {
        return new LambdaAction((builder, building) ->
            events.add(new ConstructEvent(builder, (Building)building, ConstructStatus.Complete)));
    }

    public static Action constructCancelled(Events events) {
        return new LambdaAction((building, builder) ->
            events.add(new ConstructEvent(builder, (Building)building, ConstructStatus.Cancelled)));
    }
}
