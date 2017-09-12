package com.evilbird.warcraft.item.common.animation;

import com.badlogic.gdx.math.Rectangle;

import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class AnimationSchemas
{
    public static AnimationSchema effectSchema()
    {
        List<List<Rectangle>> regions = getRegions(1, 4, 0, 0, 32, 32);
        Collections.reverse(regions.get(0));

        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 0.15f, false);
    }

    public static AnimationSchema idleSchema()
    {
        List<List<Rectangle>> regions = getRegions(8, 1, 0, 0, 72, 72);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 0.15f, true);
    }

    public static AnimationSchema idleSingualarSchema(int width, int height)
    {
        List<List<Rectangle>> regions = getRegions(1, 1, 0, 0, width, height);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 1f, true);
    }

    public static AnimationSchema hiddenSchema()
    {
        List<List<Rectangle>> regions = getRegions(1, 1, 0, 0, 1, 1);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 0.15f, true);
    }

    public static AnimationSchema constructBeginSchema(int width, int height)
    {
        List<List<Rectangle>> regions = getRegions(1, 2, 0, 0, width, height);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 3f, false);
    }

    public static AnimationSchema constructEndSchema(int width, int height)
    {
        List<List<Rectangle>> regions = getRegions(1, 2, 0, 0, width, height);
        Collections.reverse(regions.get(0));

        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 3f, false);
    }

    public static AnimationSchema gatherSchema(int width, int height)
    {
        List<List<Rectangle>> regions = getRegions(1, 2, 0, 0, width, height);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 3f, false);
    }

    public static AnimationSchema moveSchema()
    {
        List<List<Rectangle>> regions = getRegions(8, 5, 0, 0, 72, 72);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 0.15f, true);
    }

    public static AnimationSchema attackSchema()
    {
        List<List<Rectangle>> regions = getRegions(8, 4, 0, 360, 72, 72);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 0.15f, true);
    }

    public static AnimationSchema deathSchema()
    {
        List<List<Rectangle>> regions = getRegions(8, 3, 0, 648, 72, 72);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 0.15f, false);
    }

    public static AnimationSchema decomposeSchema()
    {
        List<List<Rectangle>> regions = getRegions(8, 6, 0, 0, 72, 72);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 2f, false);
    }

    private static List<List<Rectangle>> getRegions(int xCount, int yCount, int x, int y, int width, int height)
    {
        List<List<Rectangle>> result = new ArrayList<>(xCount);
        for (int xIndex = 0; xIndex < xCount; xIndex++){
            result.add(getRegions(yCount, x + (xIndex * width), y, width, height));
        }
        return result;
    }

    private static List<Rectangle> getRegions(int yCount, int x, int y, int width, int height)
    {
        List<Rectangle> result = new ArrayList<Rectangle>(yCount);
        for (int yIndex = 0; yIndex < yCount; yIndex++){
            result.add(new Rectangle(x, y + (yIndex * height), width, height));
        }
        return result;
    }

    private static Map<Range<Float>, List<Rectangle>> getFrames(List<List<Rectangle>> regions)
    {
        if (regions.size() == 1){
            Map<Range<Float>, List<Rectangle>> frames = new HashMap<Range<Float>, List<Rectangle>>();
            frames.put(Range.between(0.0f, 360.0f), regions.get(0));
            return frames;
        }
        if (regions.size() == 8) {
            Map<Range<Float>, List<Rectangle>> frames = new HashMap<>(9);
            frames.put(Range.between(112.5f, 67.5f), regions.get(0));
            frames.put(Range.between(67.5f, 22.5f), regions.get(1));
            frames.put(Range.between(22.5f, 0.0f), regions.get(2));
            frames.put(Range.between(360.0f, 337.5f), regions.get(2));
            frames.put(Range.between(337.5f, 292.5f), regions.get(3));
            frames.put(Range.between(292.5f, 247.5f), regions.get(4));
            frames.put(Range.between(247.5f, 202.5f), regions.get(5));
            frames.put(Range.between(202.5f, 157.5f), regions.get(6));
            frames.put(Range.between(157.5f, 112.5f), regions.get(7));
            return frames;
        }
        throw new UnsupportedOperationException();
    }
}
