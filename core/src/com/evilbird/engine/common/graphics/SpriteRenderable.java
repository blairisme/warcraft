/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * A {@link Renderable} implementation that renders a {@link Sprite}.
 *
 * @author Blair Butterworth
 */
public class SpriteRenderable extends Sprite implements Renderable
{
    public SpriteRenderable(SpriteRenderable sprite) {
        this(sprite.getTexture());
    }

    public SpriteRenderable(Texture texture) {
        super(texture);
    }

    @Override
    public void update(float time) {
    }
}
