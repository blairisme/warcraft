/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.scenario;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.ItemRoot;

public interface ScenarioCondition
{
    boolean applicable(EventQueue events);

    boolean evaluate(ItemRoot state);
}
