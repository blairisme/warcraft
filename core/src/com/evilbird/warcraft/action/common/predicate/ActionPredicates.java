/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.predicate;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

/**
 * Instances of this class provide common {@link Predicate Predicates} for
 * operating on {@link Action Actions}.
 *
 * @author Blair Butterworth
 */
@Deprecated
public class ActionPredicates
{
    private ActionPredicates() {
    }

    public static Predicate<Action> withinRange() {
        return new TargetWithinRange();
    }

    public static Predicate<Action> isTargetAlive() {
        return new TargetAlive();
    }

    public static Predicate<Action> targetHasResources(ResourceType type) {
        return new TargetHasResources(type);
    }
}
