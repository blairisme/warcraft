package com.evilbird.engine.common.audio;

import com.badlogic.gdx.audio.Sound;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class BasicSoundEffect implements SoundEffect
{
    private Sound delegate;
    private long instanceId;

    public BasicSoundEffect(Sound delegate)
    {
        this.delegate = delegate;
        this.instanceId = -1;
    }

    @Override
    public void play()
    {
        instanceId = delegate.play();
    }

    @Override
    public void stop()
    {
        if (instanceId != -1){
            delegate.stop(instanceId);
        }
    }
}
