/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
