/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * A renderable knows how to draw itself into a given area, defined by provided
 * size and position values.
 *
 * @author Blair Butterworth
 */
public interface Renderable
{
    void draw(Batch batch);

    void setPosition(float x, float y);

    void setSize(float x, float y);

    void update(float time);
}
