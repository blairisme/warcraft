/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.pathing;

import com.badlogic.gdx.math.GridPoint2;

/**
 * Implementors of this interface represent a single division of the game
 * space.
 *
 * @author Blair Butterworth
 */
public interface SpatialNode
{
    int getIndex();

    GridPoint2 getSpatialReference();
}
