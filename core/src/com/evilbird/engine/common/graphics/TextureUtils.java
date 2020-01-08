/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

/**
 * Provides common utility functions that operate on {@link Texture Textures}.
 *
 * @author Blair Butterworth
 */
public class TextureUtils
{
    /**
     * Disable construction of this static utility class.
     */
    private TextureUtils() {
    }

    /**
     * Generates a texture containing a 1 pixel wide rectangle of the given
     * width and height.
     *
     * @param width     the width of the resulting rectangle.
     * @param height    the height of the resulting rectangle.
     * @param colour     the colour of the resulting rectangle.
     *
     * @return a new {@link Texture}. This method will not return {@code null}.
     */
    public static Texture getRectangle(int width, int height, Color colour) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(colour);
        pixmap.drawRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
        return new Texture(pixmap);
    }

    /**
     * Alters the size of the given {@link Texture} so that it conforms to the
     * specified size.
     *
     * @param texture   the {@code Texture} to resize.
     * @param width     the width of the resulting {@code Texture}.
     * @param height    the height of the resulting {@code Texture}.
     *
     * @return a new {@code Texture}. This method will not return {@code null}.
     */
    public static Texture resizeTexture(Texture texture, int width, int height) {
        TextureData textureData = texture.getTextureData();

        Pixmap source = textureData.consumePixmap();
        Pixmap destination = new Pixmap(width, height, source.getFormat());

        destination.drawPixmap(source,
                0, 0, source.getWidth(), source.getHeight(),
                0, 0, destination.getWidth(), destination.getHeight()
        );
        Texture resized = new Texture(destination);

        source.dispose();
        destination.dispose();

        return resized;
    }
}
