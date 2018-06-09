package com.evilbird.warcraft.item.common.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.collection.Collections;
import com.evilbird.engine.common.graphics.DirectionalAnimation;

import org.apache.commons.lang3.Range;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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

    public static DirectionalAnimation combine(DirectionalAnimation source, DirectionalAnimation target)
    {
        Map<Range<Float>, Array<TextureRegion>> sourceFrameSet = source.getFrames();
        Map<Range<Float>, Array<TextureRegion>> targetFrameSet = target.getFrames();
        Map<Range<Float>, Array<TextureRegion>> combinedFrames = new HashMap<>(sourceFrameSet.size());

        for (Entry<Range<Float>, Array<TextureRegion>> sourceFrameEntry: sourceFrameSet.entrySet()){
            Range<Float> range = sourceFrameEntry.getKey();
            Array<TextureRegion> sourceFrames = sourceFrameEntry.getValue();
            Array<TextureRegion> targetFrames = targetFrameSet.get(range);
            combinedFrames.put(range, Collections.union(sourceFrames, targetFrames));
        }
        return new DirectionalAnimation(source.getDirection(), source.getDuration(), combinedFrames, source.getMode());
    }
}
