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
import com.evilbird.warcraft.item.unit.Unit;

@Deprecated
public class TargetAlive implements Predicate<Action>
{
    public TargetAlive() {
    }

    @Override
    public boolean test(Action action) {
        Unit unit = (Unit)action.getTarget();
        return unit.isAlive();
    }
}
