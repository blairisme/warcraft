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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * A {@link Renderable} implementation that renders a {@link Sprite}.
 *
 * @author Blair Butterworth
 */
public class SpriteRenderable extends SpriteDrawable implements Renderable
{
    public SpriteRenderable(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        super.draw(batch, x, y, width, height);
    }

    @Override
    public void update(float time) {
    }
}
