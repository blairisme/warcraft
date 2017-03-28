package com.evilbird.warcraft.common;

import com.badlogic.gdx.math.Rectangle;

import org.apache.commons.lang3.Range;

import java.util.List;
import java.util.Map;

/**
 * Implementors of this TODO:Finish
 *
 * @author Blair Butterworth
 */
public class AnimationLayout
{
    private float frameInterval;
    private Map<Range<Float>, List<Rectangle>> frameRegions;

    public AnimationLayout(Map<Range<Float>, List<Rectangle>> frameRegions, float frameInterval)
    {
        this.frameRegions = frameRegions;
        this.frameInterval = frameInterval;
    }

    public float getFrameInterval()
    {
        return frameInterval;
    }

    public Map<Range<Float>, List<Rectangle>> getFrameRegions()
    {
        return frameRegions;
    }
}
