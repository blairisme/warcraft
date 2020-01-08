/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.lang;

import com.badlogic.gdx.math.Vector2;

/**
 * Implementors of this interface represent an object that faces a direction.
 *
 * @author Blair Butterworth
 */
public interface Directionable extends Positionable
{
    /**
     * Sets the direction of the object, specified as a normalised direction
     * vector.
     */
    void setDirection(Vector2 direction);
}
