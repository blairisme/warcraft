/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action;

import com.evilbird.engine.game.GameFactory;

/**
 * Instances of this class provide access to {@link Action Actions}, self
 * contained "bundles" of behaviour, that modify the game state is a meaningful
 * manner.
 *
 * @author Blair Butterworth
 */
public interface ActionFactory extends GameFactory<Action>
{
}
