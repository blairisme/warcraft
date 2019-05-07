/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.LambdaAction;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

/**
 * Instances of this class provide utility functions for use in with
 * Placeholder actions.
 *
 * @author Blair Butterworth
 */
public class PlaceholderUtils
{
    private PlaceholderUtils() {
    }

    public static Action assignConstruction() {
        return new LambdaAction((subject, target) -> {
            if (subject instanceof Gatherer) {
                Gatherer gatherer = (Gatherer)subject;
                gatherer.setConstruction(target);
            }
        });
    }

    public static Action unassignConstruction() {
        return new LambdaAction((subject, target) -> {
            if (subject instanceof Gatherer) {
                Gatherer gatherer = (Gatherer)subject;
                gatherer.setConstruction(null);
            }
        });
    }
}
