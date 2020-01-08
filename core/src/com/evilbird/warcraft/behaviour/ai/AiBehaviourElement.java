/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai;

import com.evilbird.engine.object.GameObjectContainer;

/**
 * Implementors of this interface provide a method that updates the game state
 * on behalf of non-human players.
 *
 * @author Blair Butterworth
 */
public interface AiBehaviourElement
{
    void applyBehaviour(GameObjectContainer gameState, float time);
}
