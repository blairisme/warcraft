/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.LambdaAction;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import static com.evilbird.warcraft.action.gather.GatherStatus.Cancelled;
import static com.evilbird.warcraft.action.gather.GatherStatus.DepositComplete;
import static com.evilbird.warcraft.action.gather.GatherStatus.DepositStarted;
import static com.evilbird.warcraft.action.gather.GatherStatus.ObtainComplete;
import static com.evilbird.warcraft.action.gather.GatherStatus.ObtainStarted;

/**
 * Helper class for generating gather events.
 *
 * @author Blair Butterworth
 */
public class GatherEvents
{
    private GatherEvents() {
    }

    @Deprecated
    public static Action obtainStarted(Events events, ResourceQuantity resource) {
        return new LambdaAction((gatherer, source) ->
            events.add(new GatherEvent(gatherer, source, resource, ObtainStarted)));
    }

    public static void notifyObtainStarted(Events events, Item gatherer, Item resource, ResourceQuantity quantity) {
        events.add(new GatherEvent(gatherer, resource, quantity, ObtainStarted));
    }

    @Deprecated
    public static Action obtainComplete(Events events, ResourceQuantity resource) {
        return new LambdaAction((gatherer, source) ->
            events.add(new GatherEvent(gatherer, source, resource, ObtainComplete)));
    }

    public static void notifyObtainComplete(Events events, Item gatherer, Item resource, ResourceQuantity quantity) {
        events.add(new GatherEvent(gatherer, resource, quantity, ObtainComplete));
    }

    @Deprecated
    public static Action depositStarted(Events events, ResourceQuantity resource) {
        return new LambdaAction((gatherer, recipient) ->
            events.add(new GatherEvent(gatherer, recipient, resource, DepositStarted)));
    }

    public static void notifyDepositStarted(Events events,Item gatherer, Item depot, ResourceQuantity quantity) {
        events.add(new GatherEvent(gatherer, depot, quantity, DepositStarted));
    }

    @Deprecated
    public static Action depositComplete(Events events, ResourceQuantity resource) {
        return new LambdaAction((gatherer, recipient) ->
            events.add(new GatherEvent(gatherer, recipient, resource, DepositComplete)));
    }

    public static void notifyDepositComplete(Events events,Item gatherer, Item depot, ResourceQuantity quantity) {
        events.add(new GatherEvent(gatherer, depot, quantity, DepositComplete));
    }

    @Deprecated
    public static Action gatherCancelled(Events events) {
        return new LambdaAction((gatherer, source) ->
            events.add(new GatherEvent(gatherer, Cancelled)));
    }

    public static void notifyGatherCancelled(Events events, Gatherer gatherer) {
        events.add(new GatherEvent(gatherer, Cancelled));
    }
}
