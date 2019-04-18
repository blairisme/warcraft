/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.scenario.conditions;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.behaviour.scenario.ScenarioCondition;

import java.util.function.Predicate;

public class BasicCondition implements ScenarioCondition
{
    private Class<? extends Event> trigger;
    private Predicate<ItemRoot> condition;

    public BasicCondition(Class<? extends Event> trigger, Predicate<ItemRoot> condition) {
        this.trigger = trigger;
        this.condition = condition;
    }

    @Override
    public boolean applicable(EventQueue events) {
        return events.hasEvents(trigger);
    }

    @Override
    public boolean evaluate(ItemRoot state) {
        return condition.test(state);
    }
}
