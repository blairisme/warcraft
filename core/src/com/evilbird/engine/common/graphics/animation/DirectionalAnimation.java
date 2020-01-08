/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.graphics.animation;

/**
 * Represents an {@link Animation} whose direction can be specified, altering
 * the visual appearance of the animation to accordingly.
 *
 * @author Blair Butterworth
 */
public interface DirectionalAnimation extends Animation
{
    /**
     * Returns the current direction the animation is pointing, specified in
     * degrees.
     *
     * @return a positive integer between 0 and 360.
     */
    float getDirection();

    /**
     * Sets the direction of the animation, potentially altering its
     * appearance. Direction is specified in degrees.
     *
     * @param direction a direction. Must be a positive integer between 0 and
     *                  360.
     */
    void setDirection(float direction);
}
