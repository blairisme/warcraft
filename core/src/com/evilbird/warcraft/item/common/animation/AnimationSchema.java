package com.evilbird.warcraft.item.common.animation;

import com.badlogic.gdx.math.Rectangle;

import org.apache.commons.lang3.Range;

import java.util.List;
import java.util.Map;

/**
 * Implementors of this TODO:Finish
 *
 * @author Blair Butterworth
 */
public class AnimationSchema
{
    private float interval;
    private boolean loop;
    private Map<Range<Float>, List<Rectangle>> frames;

    public AnimationSchema(Map<Range<Float>, List<Rectangle>> frames, float interval, boolean loop)
    {
        this.frames = frames;
        this.interval = interval;
        this.loop = loop;
    }

    public float getFrameInterval()
    {
        return interval;
    }

    public boolean getLoop()
    {
        return loop;
    }

    public Map<Range<Float>, List<Rectangle>> getFrameRegions()
    {
        return frames;
    }
}
