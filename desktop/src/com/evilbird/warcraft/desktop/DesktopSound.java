/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
