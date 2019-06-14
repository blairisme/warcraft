/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.scenario.condition;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.ItemRoot;

import java.util.function.BiPredicate;

/**
 * Defines a condition that if fulfilled signals the completion or failure of a
 * scenario.
 *
 * @author Blair Butterworth
 */
public interface ScenarioCondition extends BiPredicate<ItemRoot, EventQueue>
{
}
