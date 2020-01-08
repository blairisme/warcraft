/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
