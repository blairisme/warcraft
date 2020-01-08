/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
        super(new Sprite(texture));
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