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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * A {@link Renderable} implementation that renders a {@link Texture} or
 * {@link TextureRegion}.
 *
 * @author Blair Butterworth
 */
public class TextureRenderable extends TextureRegionDrawable implements Renderable
{
    public TextureRenderable(Texture texture) {
        super(texture);
    }

    public TextureRenderable(TextureRegion texture) {
        super(texture);
    }

    @Override
    public void update(float time) {
    }
}
