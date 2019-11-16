/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics.renderable;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * A renderable knows how to draw itself into a given area, defined by provided
 * size and position values.
 *
 * @author Blair Butterworth
 */
public interface Renderable extends Drawable
{
    default void draw(Batch batch, Vector2 position, Vector2 size) {
        draw(batch, position.x, position.y, size.x, size.y);
    }

    void update(float time);
}
