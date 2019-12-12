/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio.sound;

/**
 * Implementors of this interface represent a sound effect, a short audio clip
 * played in response to an event in the game.
 *
 * @author Blair Butterworth
 */
public interface Sound
{
    /**
     * Plays the sound. If the sound is already playing, it will be restarted.
     */
    void play();

    /**
     * Pauses playing the sound. If the sound is no longer playing, this has no
     * effect.
     */
    void pause();

    /**
     * Plays the sound continuously. If the sound is already playing, it will
     * be restarted.
     */
    void loop();

    /**
     * Resumes playing the sound. If the sound wasn't paused, this has no effect.
     */
    void resume();

    /**
     * Stops playing the sound. If the sound is no longer playing, this has no
     * effect.
     */
    void stop();

    /**
     * Sets the volume of the playing sound.
     *
     * @param volume the volume in the range 0 (silent) to 1 (max volume).
     */
    void setVolume(float volume);
}
