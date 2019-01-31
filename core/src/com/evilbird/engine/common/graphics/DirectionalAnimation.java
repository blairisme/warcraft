package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import org.apache.commons.lang3.Range;

import java.util.Map;
import java.util.Map.Entry;

public class DirectionalAnimation
{
    private float direction;
    private float duration;
    private PlayMode mode;
    private Animation delegate;
    private Map<Range<Float>, Array<TextureRegion>> frames;

    public DirectionalAnimation(float direction, float duration, Map<Range<Float>, Array<TextureRegion>> frames, PlayMode mode)
    {
        this.direction = direction;
        this.duration = duration;
        this.mode = mode;
        this.frames = frames;
        reset();
    }

    public Map<Range<Float>, Array<TextureRegion>> getFrames() {
        return frames;
    }

    public TextureRegion getKeyFrame(float stateTime)
    {
        return delegate.getKeyFrame(stateTime);
    }

    public float getDirection()
    {
        return direction;
    }

    public float getDuration()
    {
        return duration;
    }

    public PlayMode getMode()
    {
        return mode;
    }

    public void setDirection(float direction)
    {
        this.direction = direction;
        reset();
    }

    private void reset()
    {
        this.delegate = new Animation(duration, getFrames(direction), mode);
    }

    private Array<TextureRegion> getFrames(float direction)
    {
        for (Entry<Range<Float>, Array<TextureRegion>> frameEntry: frames.entrySet()){
            if (frameEntry.getKey().contains(direction)){
                return frameEntry.getValue();
            }
        }
        return new Array<TextureRegion>();
    }
}
