/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
