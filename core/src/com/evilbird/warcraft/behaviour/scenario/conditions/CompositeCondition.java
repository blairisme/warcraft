/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.scenario.conditions;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.behaviour.scenario.ScenarioCondition;

import java.util.Collection;

public class CompositeCondition implements ScenarioCondition
{
    private Collection<ScenarioCondition> conditions;

    public CompositeCondition(Collection<ScenarioCondition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public boolean applicable(EventQueue events) {
        for (ScenarioCondition condition: conditions) {
            if (! condition.applicable(events)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean evaluate(ItemRoot state) {
        for (ScenarioCondition condition: conditions) {
            if (! condition.evaluate(state)) {
                return false;
            }
        }
        return true;
    }
}
