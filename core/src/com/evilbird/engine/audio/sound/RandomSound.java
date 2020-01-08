/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.audio.sound;

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
    public void dispose() {
        for (Sound sound: sounds) {
            sound.dispose();
        }
    }

    @Override
    public boolean isPlaying() {
        if (current != null) {
            return current.isPlaying();
        }
        return false;
    }

    @Override
    public void play() {
        if (sounds.size() > 0) {
            current = sounds.get(random.nextInt(sounds.size()));
            current.play();
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
