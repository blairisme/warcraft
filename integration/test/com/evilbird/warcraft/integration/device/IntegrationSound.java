/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
