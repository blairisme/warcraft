/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.control;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Objects;

/**
 * Defines common utility functions that operate on LibGDX controls.
 *
 * @author Blair Butterworth
 */
public class ControlUtils
{
    /**
     * Disable construction of static utility class.
     */
    private ControlUtils() {
    }

    /**
     * Returns the position of the given {@link Actor} relative to its highest
     * level parent.
     */
    public static Vector2 getScreenPosition(Actor actor) {
        Objects.requireNonNull(actor);

        Vector2 position = new Vector2(actor.getX(), actor.getY());
        Actor parent = actor.getParent();

        while (parent != null) {
            position.add(parent.getX(), parent.getY());
            parent = parent.getParent();
        }
        return position;
    }
}
