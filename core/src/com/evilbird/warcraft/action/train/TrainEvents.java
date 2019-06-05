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
import com.evilbird.engine.events.Events;
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

    public static Action onTrainStarted(Events events) {
        return new LambdaAction((item, target) ->
            events.add(new TrainEvent((Building)item, TrainStatus.Started)));
    }

    public static Action onTrainCompleted(Events events) {
        return new LambdaAction((item, target) ->
            events.add(new TrainEvent((Building)item, TrainStatus.Complete)));
    }

    public static Action onTrainCancelled(Events events) {
        return new LambdaAction((item, target) ->
            events.add(new TrainEvent((Building)item, TrainStatus.Cancelled)));
    }
}
