/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.animation;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.evilbird.engine.common.graphics.AnimationSchema;
import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines animation frame dimensions and layouts.
 *
 * @author Blair Butterworth
 */
public class AnimationLayouts
{
    private AnimationLayouts() {
    }

    public static AnimationSchema effectSchema() {
        List<List<Rectangle>> regions = getRegions(1, 4, 0, 0, 32, 32);
        Collections.reverse(regions.get(0));

        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 0.15f, false);
    }

    public static AnimationSchema idleSchema() {
        return idleSchema(72, 72);
    }

    public static AnimationSchema idleSchema(GridPoint2 size) {
        return idleSchema(size.x, size.y);
    }

    public static AnimationSchema idleSchema(int width, int height) {
        List<List<Rectangle>> regions = getRegions(8, 1, 0, 0, width, height);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 0.15f, true);
    }

    public static AnimationSchema idleSingularSchema(int width, int height) {
        List<List<Rectangle>> regions = getRegions(1, 1, 0, 0, width, height);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 1f, true);
    }

    public static AnimationSchema constructStaticSchema(int width, int height) {
        List<List<Rectangle>> regions = getRegions(1, 1, 0, 0, width, height);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 1f, false);
    }

    public static AnimationSchema constructBeginSchema(int width, int height) {
        List<List<Rectangle>> regions = getRegions(1, 2, 0, 0, width, height);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 8f, false);
    }

    public static AnimationSchema constructEndSchema(int width, int height) {
        List<List<Rectangle>> regions = getRegions(1, 2, 0, 0, width, height);
        Collections.reverse(regions.get(0));

        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 8f, false);
    }

    public static AnimationSchema gatheringSchema(int width, int height) {
        List<List<Rectangle>> regions = getRegions(1, 1, 0, height, width, height);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 3f, false);
    }

    public static AnimationSchema moveSchema() {
        List<List<Rectangle>> regions = getRegions(8, 5, 0, 0, 72, 72);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 0.10f, true);
    }

    public static AnimationSchema gatherWoodSchema() {
        Map<Range<Float>, List<Rectangle>> frames = getFrames(getRegions(8, 4, 0, 360, 72, 72));
        return new AnimationSchema(frames, 0.15f, true);
    }

    public static AnimationSchema meleeAttackSchema() {
        Map<Range<Float>, List<Rectangle>> attack = getFrames(getRegions(8, 4, 0, 360, 72, 72));
        Map<Range<Float>, List<Rectangle>> idle = getFrames(getRegions(8, 1, 0, 0, 72, 72));
        Map<Range<Float>, List<Rectangle>> frames = combineFrames(attack, idle);
        return new AnimationSchema(frames, 0.15f, false);
    }

    public static AnimationSchema rangedAttackSchema() {
        Map<Range<Float>, List<Rectangle>> attack = getFrames(getRegions(8, 2, 0, 360, 72, 72));
        Map<Range<Float>, List<Rectangle>> idle = getFrames(getRegions(8, 1, 0, 0, 72, 72));
        Map<Range<Float>, List<Rectangle>> frames = combineFrames(attack, idle);
        return new AnimationSchema(frames, 0.15f, false);
    }

    public static AnimationSchema buildingDestructionScheme() {
        List<List<Rectangle>> regions = getRegions(1, 1, 0, 0, 64, 64);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 1f, true);
    }

    public static AnimationSchema deathSchema() {
        List<List<Rectangle>> regions = getRegions(8, 3, 0, 648, 72, 72);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 0.15f, false);
    }

    public static AnimationSchema boatDeathSchema() {
        List<List<Rectangle>> regions = getRegions(8, 2, 0, 88, 80, 88);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 0.5f, false);
    }

    public static AnimationSchema critterDeathSchema() {
        List<List<Rectangle>> regions = getRegions(1, 1, 0, 32, 32, 32);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 0.5f, false);
    }

    public static AnimationSchema decomposeSchema() {
        List<List<Rectangle>> regions = getRegions(8, 6, 0, 0, 72, 72);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 2f, false);
    }

    public static AnimationSchema boatDecomposeSchema() {
        List<List<Rectangle>> regions = getRegions(1, 1, 0, 432, 72, 72);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 2f, false);
    }

    public static AnimationSchema projectileStaticSchema() {
        List<List<Rectangle>> regions = getRegions(8, 1, 0, 0, 40, 40);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 10f, false);
    }

    public static AnimationSchema projectileAnimatedSchema() {
        List<List<Rectangle>> regions = getRegions(1, 3, 0, 0, 32, 32);
        Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
        return new AnimationSchema(frames, 0.2f, false);
    }

    private static List<List<Rectangle>> getRegions(int xCount, int yCount, int x, int y, int width, int height) {
        List<List<Rectangle>> result = new ArrayList<>(xCount);
        for (int xIndex = 0; xIndex < xCount; xIndex++) {
            result.add(getRegions(yCount, x + (xIndex * width), y, width, height));
        }
        return result;
    }

    private static List<Rectangle> getRegions(int yCount, int x, int y, int width, int height) {
        List<Rectangle> result = new ArrayList<Rectangle>(yCount);
        for (int yIndex = 0; yIndex < yCount; yIndex++) {
            result.add(new Rectangle(x, y + (yIndex * height), width, height));
        }
        return result;
    }

    private static Map<Range<Float>, List<Rectangle>> getFrames(List<List<Rectangle>> regions) {
        if (regions.size() == 1) {
            Map<Range<Float>, List<Rectangle>> frames = new HashMap<>();
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

    private static Map<Range<Float>, List<Rectangle>> combineFrames(
            Map<Range<Float>, List<Rectangle>> framesA,
            Map<Range<Float>, List<Rectangle>> framesB)
    {
        Map<Range<Float>, List<Rectangle>> result = new HashMap<>();
        for (Map.Entry<Range<Float>, List<Rectangle>> entry: framesA.entrySet()) {
            Range<Float> direction = entry.getKey();
            List<Rectangle> frames = new ArrayList<>();
            frames.addAll(entry.getValue());
            frames.addAll(framesB.get(direction));
            result.put(direction, frames);
        }
        return result;
    }
}
