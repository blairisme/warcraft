/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.behaviour;

import com.evilbird.engine.common.lang.Identifiable;

/**
 * Implementors of this interface define game logic that decides which
 * modifications to the game state should be made in response to user input,
 * time and other factors.
 *
 * @author Blair Butterworth
 */
public interface Behaviour extends BehaviourElement, Identifiable<BehaviourIdentifier>
{
}
