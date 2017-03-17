package com.evilbird.warcraft.common;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class TextureUtils
{
    public static Drawable getDrawable(AssetManager assets, String path)
    {
        Texture iconTexture = assets.get(path, Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture);
        return new TextureRegionDrawable(iconRegion);
    }

    public static Drawable getDrawable(AssetManager assets, String path, int x, int y, int width, int height)
    {
        Texture iconTexture = assets.get(path, Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, x, y, width, height);
        return new TextureRegionDrawable(iconRegion);
    }
}
