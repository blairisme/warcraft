package com.evilbird.warcraft.common;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.graphics.DirectionalAnimation;

import org.apache.commons.lang3.Range;

import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class AnimationUtils
{
    public static DirectionalAnimation getAnimation(TextureRegion texture)
    {
        Array<TextureRegion> textures = Array.with(texture);
        Map<Range<Float>, Array<TextureRegion>> frames = new HashMap<Range<Float>, Array<TextureRegion>>(1);
        frames.put(Range.between(0.0f, 360.0f), textures);
        return new DirectionalAnimation(0f, Float.MAX_VALUE, frames, Animation.PlayMode.LOOP);
    }

    public static Drawable getDrawable(AssetManager assets, String path, int x, int y, int width, int height)
    {
        Texture iconTexture = assets.get(path, Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, x, y, width, height);
        return new TextureRegionDrawable(iconRegion);
    }
}
