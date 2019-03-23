/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

/**
 * Instances of this class provide common utilities for working with textures.
 *
 * @author Blair Butterworth
 */
public class TextureUtils
{
    private TextureUtils() {
    }

    public static Drawable getDrawable(Color colour) {
        Pixmap background = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        background.setColor(colour);
        background.fill();

        Texture texture = new Texture(background);
        TextureRegion region = new TextureRegion(texture);
        return new TiledDrawable(region);
    }

    public static Drawable getDrawable(AssetManager assets, String path) {
        Texture texture = assets.get(path, Texture.class);
        TextureRegion region = new TextureRegion(texture);
        return new TextureRegionDrawable(region);
    }

    public static Drawable getDrawable(AssetManager assets, String path, int x, int y, int width, int height) {
        Texture texture = assets.get(path, Texture.class);
        TextureRegion region = new TextureRegion(texture, x, y, width, height);
        return new TextureRegionDrawable(region);
    }

    public static Drawable getTiledDrawable(AssetManager assets, String path) {
        Texture texture = assets.get(path, Texture.class);
        TextureRegion region = new TextureRegion(texture);
        return new TiledDrawable(region);
    }

    public static TextureRegion getRegion(AssetManager assets, String path, int x, int y, int width, int height) {
        Texture texture = assets.get(path, Texture.class);
        return new TextureRegion(texture, x, y, width, height);
    }
}
