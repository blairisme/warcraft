/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics.renderable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * A {@link Renderable} implementation that renders a {@link Sprite}.
 *
 * @author Blair Butterworth
 */
public class SpriteRenderable extends SpriteDrawable implements Renderable
{
    public SpriteRenderable(Texture texture) {
        this(new Sprite(texture));
    }

    public SpriteRenderable(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void update(float time) {
    }
}
