/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.scenario.supplement;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObjectContainer;

import java.util.function.BiConsumer;

/**
 * Defines additional behaviour required by a scenario.
 *
 * @author Blair Butterworth
 */
public interface SupplementaryBehaviour extends BiConsumer<GameObjectContainer, EventQueue>
{
}
