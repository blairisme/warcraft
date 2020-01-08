/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import org.apache.commons.lang3.Validate;

/**
 * Provides common utility functions that operate on {@link Drawable Drawables}.
 *
 * @author Blair Butterworth
 */
public class DrawableUtils
{
    /**
     * Disable construction of this static utility class.
     */
    private DrawableUtils() {
    }

    /**
     * Generates a {@link Drawable} for the given {@link Color Colour}.
     * Specifically a {@link Pixmap} is used to create a texture which is then
     * wrapped in a {@link TiledDrawable}, allowing the texture to be displayed
     * at any desired size.
     *
     * @param colour the {@code Colour} filling the desired {@code Drawable}.
     * @return       a {@code Drawable}.
     */
    public static Drawable getDrawable(Color colour) {
        Validate.notNull(colour);

        Pixmap background = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        background.setColor(colour);
        background.fill();

        Texture texture = new Texture(background);
        TextureRegion region = new TextureRegion(texture);
        return new TiledDrawable(region);
    }

    /**
     * Loads the texture at the given path using the {@link AssetManager} and
     * generates a new {@link Drawable} it. The resulting {@code Drawable} will
     * be sized to that of the loaded asset.
     *
     * @param assets    an {@code AssetManager} into which the asset at the
     *                  given path will be loaded. This parameter cannot be
     *                  {@code null}.
     * @param path      the path to an asset whose contents will be shown in
     *                  the resulting drawable. This parameter cannot be
     *                  {@code null}.
     *
     * @return a new {@code Drawable}. This method will not return {@code null}.
     */
    public static Drawable getDrawable(AssetManager assets, String path) {
        Validate.notNull(assets);
        Validate.notNull(path);

        Texture texture = assets.get(path, Texture.class);
        TextureRegion region = new TextureRegion(texture);
        return new TextureRegionDrawable(region);
    }

    public static Drawable getDrawable(AssetManager assets, String path, Rectangle bounds) {
        return getDrawable(assets, path, (int)bounds.x, (int)bounds.y, (int)bounds.width, (int)bounds.height);
    }

    public static Drawable getDrawable(AssetManager assets, String path, int x, int y, int width, int height) {
        Validate.notNull(assets);
        Validate.notNull(path);

        Texture texture = assets.get(path, Texture.class);
        TextureRegion region = new TextureRegion(texture, x, y, width, height);
        return new TextureRegionDrawable(region);
    }

    public static Drawable getDrawable(Texture texture, GridPoint2 location, GridPoint2 size) {
        TextureRegion region = new TextureRegion(texture, location.x, location.y, size.x, size.y);
        return new TextureRegionDrawable(region);
    }

    public static Drawable getDrawable(Texture texture) {
        TextureRegion region = new TextureRegion(texture);
        return new TextureRegionDrawable(region);
    }

    public static Drawable getTiledDrawable(AssetManager assets, String path) {
        Validate.notNull(assets);
        Validate.notNull(path);

        Texture texture = assets.get(path, Texture.class);
        TextureRegion region = new TextureRegion(texture);
        return new TiledDrawable(region);
    }
}
