/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
