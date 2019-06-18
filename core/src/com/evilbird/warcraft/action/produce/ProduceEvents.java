/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.LambdaAction;
import com.evilbird.engine.events.Events;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Helper class for generating production events.
 *
 * @author Blair Butterworth
 */
public class ProduceEvents
{
    private ProduceEvents() {
    }

    public static Action onProductionStarted(Events events) {
        return new LambdaAction((item, target) ->
            events.add(new ProduceEvent((Building)item, ProduceStatus.Started)));
    }

    public static Action onProductionCompleted(Events events) {
        return new LambdaAction((item, target) ->
            events.add(new ProduceEvent((Building)item, ProduceStatus.Complete)));
    }

    public static Action onProductionCancelled(Events events) {
        return new LambdaAction((item, target) ->
            events.add(new ProduceEvent((Building)item, ProduceStatus.Cancelled)));
    }
}
