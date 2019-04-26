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

    public static Action constructStarted(ConstructReporter reporter) {
        return new LambdaAction((builder, building) ->
            reporter.onConstructionStarted(builder, (Building)building));
    }

    public static Action constructCompleted(ConstructReporter reporter) {
        return new LambdaAction((builder, building) ->
            reporter.onConstructionCompleted(builder, (Building)building));
    }

    public static Action constructCancelled(ConstructReporter reporter) {
        return new LambdaAction((building, target) ->
            reporter.onConstructionCancelled(null, (Building)building));
    }
}
