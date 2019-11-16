/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics.renderable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.evilbird.engine.common.time.GameTimer;

/**
 * A {@link Renderable} implementation that modifies a given {@link Texture
 * Textures} alpha value over time cycling between full visible and invisible.
 *
 * @author Blair Butterworth
 */
public class FlashingRenderable extends SpriteRenderable
{
    private GameTimer timer;
    private float modifier;

    public FlashingRenderable(Texture texture) {
        super(texture);
        this.modifier = 0.05f;
        this.timer = new GameTimer(0.04f);
    }

    @Override
    public void update(float time) {
        if (timer.advance(time)) {
            timer.reset();

            Sprite sprite = getSprite();
            Color tint = sprite.getColor();
            tint.a += modifier;
            tint.a = MathUtils.clamp(tint.a, 0, 1);
            sprite.setColor(tint);

            if (tint.a == 0 || tint.a == 1f) {
                modifier *= -1;
            }
        }
    }
}