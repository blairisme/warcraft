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
 * Implementors of this interface represent an object that occupies a position
 * in the game space.
 *
 * @author Blair Butterworth
 */
public interface Positionable
{
    /**
     * Returns the current position of the positionable.
     *
     * @return a {@link Vector2 position}.
     */
    Vector2 getPosition();

    /**
     * Sets the position of the positionable.
     *
     * @param position a {@link Vector2 position}.
     */
    void setPosition(Vector2 position);
}
