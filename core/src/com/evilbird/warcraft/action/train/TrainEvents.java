/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.LambdaAction;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Helper class for generating training events.
 *
 * @author Blair Butterworth
 */
public class TrainEvents
{
    private TrainEvents() {
    }

    public static Action onTrainStarted(TrainReporter reporter) {
        return new LambdaAction((item, target) ->
            reporter.onTrainStarted((Building)item));
    }

    public static Action onTrainCompleted(TrainReporter reporter) {
        return new LambdaAction((item, target) ->
            reporter.onTrainCompleted((Building)item));
    }

    public static Action onTrainCancelled(TrainReporter reporter) {
        return new LambdaAction((item, target) ->
            reporter.onTrainCancelled((Building)item));
    }
}
