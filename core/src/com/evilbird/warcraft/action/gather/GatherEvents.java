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
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

public class GatherEvents
{
    private GatherEvents() {
    }

    public static Action obtainStarted(GatherReporter reporter, ResourceQuantity resource) {
        return new LambdaAction((gatherer, source) ->
            reporter.onObtainStarted((Gatherer)gatherer, source, resource.getResource()));
    }

    public static Action obtainComplete(GatherReporter reporter, ResourceQuantity resource) {
        return new LambdaAction((gatherer, source) ->
            reporter.onObtainComplete((Gatherer)gatherer, source, resource.getResource(), resource.getValue()));
    }

    public static Action depositStarted(GatherReporter reporter, ResourceQuantity resource) {
        return new LambdaAction((gatherer, source) ->
            reporter.onDepositStarted((Gatherer)gatherer, source, resource.getResource(), resource.getValue()));
    }

    public static Action depositComplete(GatherReporter reporter, ResourceQuantity resource) {
        return new LambdaAction((gatherer, source) ->
            reporter.onDepositComplete((Gatherer)gatherer, source, resource.getResource()));
    }

    public static Action gatherCancelled(GatherReporter reporter) {
        return new LambdaAction((gatherer, source) -> reporter.onGatherCancelled((Gatherer)gatherer));
    }
}
