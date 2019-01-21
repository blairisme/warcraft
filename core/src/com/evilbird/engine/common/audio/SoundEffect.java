/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio;

/**
 * Implementors of this interface represent a sound effect, a short audio clip
 * played in response to an event in the game.
 *
 * @author Blair Butterworth
 */
public interface SoundEffect
{
    void play();

    void stop();
}
