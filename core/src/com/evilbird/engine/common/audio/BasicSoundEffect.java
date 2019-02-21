/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio;

import com.badlogic.gdx.audio.Sound;

/**
 * Implementors of this class represent a sound effect, a short audio clip
 * played in response to an event in the game.
 *
 * @author Blair Butterworth
 */
public class BasicSoundEffect implements SoundEffect
{
    private String path;
    private Sound delegate;
    private long instanceId;

    public BasicSoundEffect(Sound delegate, String path) {
        this.path = path;
        this.delegate = delegate;
        this.instanceId = -1;
    }

    @Override
    public void play() {
        instanceId = delegate.play();
    }

    @Override
    public void stop() {
        if (instanceId != -1){
            delegate.stop(instanceId);
        }
    }

    public String getPath() {
        return path;
    }
}
