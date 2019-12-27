/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.desktop;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.backends.lwjgl.audio.OpenALSound;
import com.evilbird.engine.audio.sound.AbstractSound;
import com.evilbird.engine.audio.sound.Sound;
import org.lwjgl.openal.AL10;

/**
 * A {@link Sound} implementation for use on desktop platforms.
 *
 * @author Blair Butterworth
 */
public class DesktopSound extends AbstractSound
{
    public DesktopSound(FileHandleResolver resolver, String path) {
        super(resolver, path);
    }

    @Override
    public boolean isPlaying() {
        return AL10.alGetSourcei((int)identifier, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
    }

    public float getDuration() {
        OpenALSound sound = (OpenALSound)delegate;
        return sound.duration();
    }
}
