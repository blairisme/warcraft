/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.audio.sound;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Instances of this class represent a collection of related and
 * interchangeable sound effects, one of which will be chosen to be played.
 *
 * @author Blair Butterworth
 */
public class RandomSound implements Sound
{
    private static final transient Random random = new Random();

    private Sound current;
    private List<Sound> sounds;

    /**
     * Constructs a new instance of this class, playing one of the given
     * {@link Sound Sounds} at random when played.
     *
     * @param soundSet a {@link Collection} of {@code Sounds}.
     *
     * @throws NullPointerException if the given {@code Collection} is
     *                              {@code null}.
     */
    public RandomSound(Collection<Sound> soundSet) {
        Validate.notNull(soundSet);
        Validate.notEmpty(soundSet);

        sounds = new ArrayList<>(soundSet);
        current = sounds.get(random.nextInt(sounds.size()));
    }

    @Override
    public void dispose() {
        for (Sound sound: sounds) {
            sound.dispose();
        }
    }

    public List<Sound> getSounds() {
        return Collections.unmodifiableList(sounds);
    }

    @Override
    public boolean isPlaying() {
        return current.isPlaying();
    }

    @Override
    public void play() {
        current = sounds.get(random.nextInt(sounds.size()));
        current.play();
    }

    @Override
    public void stop() {
        current.stop();
    }

    @Override
    public void setVolume(float volume) {
        for (Sound sound : sounds) {
            sound.setVolume(volume);
        }
    }
}
