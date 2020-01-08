/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object;

import com.evilbird.engine.game.GameFactory;

/**
 * Implementors of factory interface provide methods that create
 * {@link GameObject} instances.
 *
 * @author Blair Butterworth
 */
public interface GameObjectFactory extends GameFactory<GameObject>
{
}
