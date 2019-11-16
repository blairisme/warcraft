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

/**
 * A {@link Renderable} implementation that doesn't do anything when drawn.
 *
 * @author Blair Butterworth
 */
public class EmptyRenderable extends BaseRenderable
{
    public static final EmptyRenderable BlankRenderable = new EmptyRenderable();

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
    }

    @Override
    public void update(float time) {
    }
}
