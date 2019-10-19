/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.math.Rectangle;
import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Builds {@link AnimationLayout AnimationLayouts}.
 */
public class AnimationLayoutBuilder
{
    private int width;
    private int height;
    private int directions;
    private float interval;
    private boolean looping;
    private boolean reversed;
    private LinkedHashMap<Integer, Integer> sequences;

    public AnimationLayoutBuilder() {
        width = 0;
        height = 0;
        interval = 0;
        directions = 8;
        looping = false;
        reversed = false;
        sequences = new LinkedHashMap<>(2);
    }

    public AnimationLayout build() {
        return new AnimationLayout(getFrames(sequences, width, height), interval, looping);
    }

    public void setDirections(int directions) {
        this.directions = directions;
    }

    public void setInterval(float interval) {
        this.interval = interval;
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    public void setReversed() {
        this.reversed = true;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void addSequence(int start, int count) {
        sequences.put(start, count);
    }

    private Map<Range<Float>, List<Rectangle>> getFrames(Map<Integer, Integer> sequences, int width, int height) {
        Map<Range<Float>, List<Rectangle>> result = new HashMap<>(sequences.size());
        for (Entry<Integer, Integer> sequence: sequences.entrySet()) {
            int start = sequence.getValue();
            int count = sequence.getKey();
            List<List<Rectangle>> regions = getRegions(directions, start, 0, count, width, height, reversed);
            Map<Range<Float>, List<Rectangle>> frames = getFrames(regions);
            result = combineFrames(result, frames);
        }
        return result;
    }

    private Map<Range<Float>, List<Rectangle>> getFrames(List<List<Rectangle>> regions) {
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

    private Map<Range<Float>, List<Rectangle>> combineFrames(
            Map<Range<Float>, List<Rectangle>> framesA,
            Map<Range<Float>, List<Rectangle>> framesB)
    {
        if (framesA.isEmpty()) {
            return framesB;
        }
        if (framesB.isEmpty()) {
            return framesA;
        }
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

    private List<List<Rectangle>> getRegions(
        int xCount, int yCount, int x, int y, int width, int height, boolean reversed)
    {
        List<List<Rectangle>> result = new ArrayList<>(xCount);
        for (int xIndex = 0; xIndex < xCount; xIndex++) {
            result.add(reversed
                ? reverseSequence(yCount, x + (xIndex * width), y, width, height)
                : forwardSequence(yCount, x + (xIndex * width), y, width, height));
        }
        return result;
    }

    private List<Rectangle> forwardSequence(int yCount, int x, int y, int width, int height) {
        List<Rectangle> result = new ArrayList<>(yCount);
        for (int yIndex = 0; yIndex < yCount; yIndex++) {
            result.add(new Rectangle(x, y + (yIndex * height), width, height));
        }
        return result;
    }

    private List<Rectangle> reverseSequence(int yCount, int x, int y, int width, int height) {
        List<Rectangle> result = new ArrayList<>(yCount);
        for (int yIndex = yCount - 1; yIndex >= 0; yIndex--) {
            result.add(new Rectangle(x, y + (yIndex * height), width, height));
        }
        return result;
    }
}
