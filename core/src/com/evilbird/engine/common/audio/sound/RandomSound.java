/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio.sound;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Instances of this class represent a collection of related and
 * interchangeable sound effects, one of which will be chosen to be played.
 *
 * @author Blair Butterworth
 */
public class RandomSound implements Sound
{
    private Random random;
    private Sound current;
    private List<Sound> sounds;

    /**
     * Constructs a new instance of this class, playing one of the given
     * {@link Sound Sounds} at random when played.
     *
     * @param sounds a {@link Collection} of {@code Sounds}.
     *
     * @throws NullPointerException if the given {@code Collection} is
     *                              {@code null}.
     */
    public RandomSound(Collection<Sound> sounds) {
        Objects.requireNonNull(sounds);

        this.current = null;
        this.random = new Random();
        this.sounds = new ArrayList<>(sounds);
    }

    @Override
    public void loop() {
        current = sounds.get(random.nextInt(sounds.size()));
        current.loop();
    }

    @Override
    public void play() {
        current = sounds.get(random.nextInt(sounds.size()));
        current.play();
    }

    @Override
    public void pause() {
        if (current != null){
            current.pause();
        }
    }

    @Override
    public void resume() {
        if (current != null){
            current.resume();
        }
    }

    @Override
    public void stop() {
        if (current != null){
            current.stop();
        }
    }

    @Override
    public void setVolume(float volume) {
        if (current != null){
            current.setVolume(volume);
        }
    }

    public List<Sound> getSounds() {
        return sounds;
    }
}
