package com.evilbird.engine.common.audio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SoundEffectSet implements SoundEffect
{
    private Random random;
    private SoundEffect current;
    private List<SoundEffect> sounds;

    public SoundEffectSet(Collection<SoundEffect> sounds)
    {
        this.current = null;
        this.random = new Random();
        this.sounds = new ArrayList<SoundEffect>(sounds);
    }

    @Override
    public void play()
    {
        current = sounds.get(random.nextInt(sounds.size()));
        current.play();
    }

    @Override
    public void stop()
    {
        if (current != null){
            current.stop();
        }
    }
}
