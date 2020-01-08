/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.utils;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.evilbird.engine.assets.SoundLoader;
import com.evilbird.engine.audio.sound.Sound;

/**
 * Loads test sound assets.
 *
 * @author Blair Butterworth
 */
public class MockSoundLoader extends SoundLoader
{
    public MockSoundLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    protected Sound newSound(FileHandleResolver resolver, String path) {
        return new MockSound(resolver, path);
    }
}
