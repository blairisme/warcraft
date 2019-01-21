/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Instances of this class represent a collection of sound effects, played in
 * sequence.
 *
 * @author Blair Butterworth
 */
public class SoundEffectSet implements SoundEffect
{
    private Random random;
    private SoundEffect current;
    private List<SoundEffect> sounds;

    public SoundEffectSet(Collection<SoundEffect> sounds) {
        this.current = null;
        this.random = new Random();
        this.sounds = new ArrayList<>(sounds);
    }

    @Override
    public void play() {
        current = sounds.get(random.nextInt(sounds.size()));
        current.play();
    }

    @Override
    public void stop() {
        if (current != null){
            current.stop();
        }
    }
}
