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
 * A {@link Renderable} implementation that doesn't do anything when drawn.
 *
 * @author Blair Butterworth
 */
public class EmptyRenderable implements Renderable
{
    public static final EmptyRenderable BlankRenderable = new EmptyRenderable();

    @Override
    public void draw(Batch batch) {
    }

    @Override
    public void setPosition(float x, float y) {
    }

    @Override
    public void setSize(float x, float y) {
    }

    @Override
    public void update(float time) {
    }
}
