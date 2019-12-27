/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.integration.device;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.evilbird.engine.audio.sound.AbstractSound;
import com.evilbird.engine.audio.sound.Sound;

/**
 * A {@link Sound} implementation for use in integration tests.
 *
 * @author Blair Butterworth
 */
public class IntegrationSound extends AbstractSound
{
    public IntegrationSound(FileHandleResolver resolver, String path) {
        super(resolver, path);
    }

    @Override
    public boolean isPlaying() {
        return false;
    }
}
