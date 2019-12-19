/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
